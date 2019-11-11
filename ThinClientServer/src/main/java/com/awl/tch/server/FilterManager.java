package com.awl.tch.server;

import com.awl.tch.controller.AbstractController;
import com.awl.tch.filter.Filter;

public class FilterManager {


	FilterChainImpl filterChain;

	public FilterManager(AbstractController target){
		filterChain = new FilterChainImpl();
		filterChain.setTarget(target);
	}
	public void setFilter(Filter filter){
		filterChain.addFilter(filter);
	}

	public void filterRequest(Request request,Response response){
		filterChain.doFilter(request, response);
	}

}
