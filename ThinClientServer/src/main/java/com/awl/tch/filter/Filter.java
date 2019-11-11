package com.awl.tch.filter;

import com.awl.tch.server.FilterChain;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;

public interface Filter {
	
	void doFilter(Request request, Response response, FilterChain chain) ;

}
