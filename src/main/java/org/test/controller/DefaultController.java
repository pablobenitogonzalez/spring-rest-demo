package org.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class DefaultController {

	@Autowired
	private ReloadableResourceBundleMessageSource resourceBundle;

	@RequestMapping("/**")
	public void unmappedRequest(HttpServletRequest request) {
		String uri = request.getRequestURI();
		throw new ResourceNotFoundException(this.resourceBundle.getMessage("org.test.demo.detail.no.resource.for.path",
																		   new Object[]{uri}, Locale.getDefault()));
	}
}
