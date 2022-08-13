package ua.lviv.lgs.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.shared.FilterService;

/**
 * Servlet Filter implementation class CreateBucketFilter
 */
@WebFilter("/createProduct.jsp")
public class CreateProductFilter implements Filter {

    FilterService filterService = FilterService.getFilterService();
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		filterService.doFilterValidation(request, response, chain, Arrays.asList(UserRole.ADMIN));
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
