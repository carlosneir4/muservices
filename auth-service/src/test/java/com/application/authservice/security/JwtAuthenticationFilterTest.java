package com.application.authservice.security;

import com.application.authservice.AuthServiceApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.filter.OncePerRequestFilter;
import static org.hamcrest.Matchers.contains;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServiceApplication.class)
@TestPropertySource(locations="classpath:application-test.properties")
public class JwtAuthenticationFilterTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockFilterChain chain;
    private OncePerRequestFilter filter;
    private HttpServlet servlet;

    private List<OncePerRequestFilter> invocations;

    @Before
    @SuppressWarnings("serial")
    public  void setup() {
        this.servlet = new HttpServlet() {
        };
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
        this.chain = new MockFilterChain();
        this.invocations = new ArrayList<>();
        this.filter = new OncePerRequestFilter() {
            @Override
            protected   void doFilterInternal(HttpServletRequest request,
                                              HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                JwtAuthenticationFilterTest.this.invocations.add(this);
                filterChain.doFilter(request, response);
            }
        };
    }

    @Test
    public  void doFilterOnce() throws ServletException, IOException {
        this.filter.doFilter(this.request, this.response, this.chain);
        Assert.assertThat(this.invocations, contains( this.filter ));
    }

    @Test
    public  void doFilterMultiOnlyIvokesOnce() throws ServletException, IOException {
        this.filter.doFilter(this.request, this.response,
                new MockFilterChain(this.servlet, this.filter));

        Assert.assertThat(this.invocations, contains( this.filter ));
    }

    @Test
    public  void doFilterOtherSubclassInvoked() throws ServletException, IOException {
        OncePerRequestFilter filter2 = new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                JwtAuthenticationFilterTest.this.invocations.add(this);
                filterChain.doFilter(request, response);
            }
        };
        this.filter.doFilter(this.request, this.response,
                new MockFilterChain(this.servlet, filter2));
        Assert.assertThat(this.invocations, contains( this.filter,filter2 ));
    }
}
