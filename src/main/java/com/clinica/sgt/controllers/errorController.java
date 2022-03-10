// package com.clinica.sgt.controllers;

// import org.springframework.boot.web.servlet.error.ErrorController;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.servlet.ModelAndView;
// import javax.servlet.http.HttpServletRequest;
// import java.util.Enumeration;
// import java.util.Map;

// @Controller
// public class errorController implements ErrorController {

// 	@RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
// 	public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
// 		ModelAndView errorPagina = new ModelAndView("error");
// 		String errorMensaje = " ";
// 		int httpErrorCode = getErrorCode(httpRequest);

// 		switch (httpErrorCode) {
// 		case 403: {
// 			errorMensaje = "Ocurrio un error interno";
// 			break;
// 		}
// 		case 404: {
// 			errorMensaje = "Not Found";
// 			break;
// 		}
// }

// 		errorPagina.addObject("errorcode", httpErrorCode);
// 		errorPagina.addObject("message", errorMensaje);
// 		return errorPagina;
// }

// 	  private int getErrorCode(HttpServletRequest httpRequest) {
// 		Map mapa = httpRequest.getParameterMap();
// 		for (Object key : mapa.keySet()) {
// 			String[] values = (String[]) mapa.get(key);
// 			for (String value : values) {
// 				System.out.println(key.toString() + ": " + value);
// 			}
// 		}

// 		Enumeration<String> atributes = httpRequest.getAttributeNames();
// 		while (atributes.hasMoreElements()) {
// 			String key = atributes.nextElement();
// 			System.out.println(key + ":" + httpRequest.getAttribute(key));
// 		}

// 		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
// }

// 	public String getErrorPath()  {
// 		return "/error";
// 	   }
// }
