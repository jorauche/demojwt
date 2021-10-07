//package com.abycuela.magica.configuracion;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import lombok.extern.slf4j.Slf4j;
//
//
//@Slf4j
//@Component
//public class AbichuelaStubLoggingFilter extends OncePerRequestFilter{
//
//	 public static final String ALGORITMO_HASH="SHA-256";
//	    public static final String LLAVE_HASH="fb48b5619accfdb6ef9315560e0fff8a9c927ad39986241b55558f57efbd3529";
//
//	    @Override
//	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//	    	String username = request.getParameter("usuario");
//	    	String pass = request.getParameter("password");
//	    	
//	    	//	        log.info("header {}", request.getHeader("DATA"));
////	        String llave = request.getHeader("DATA");
////	        if(llave.equals(LLAVE_HASH)){
////	            log.info("Servicio permitido");
////	            filterChain.doFilter(request, httpServletResponse);
////	        }else
////	            throw new ServletException("No tiene permitido consumir este servicio");
//
//	        log.info("Request URI is: " + request.getRequestURI());
//	    }
//
////	    private static String generaHast(){
////	        String hashLlave="SUPERWALLET";
////	        String llaveSha256 = DigestUtils.sha256Hex(hashLlave);
////	        log.info(llaveSha256);
////	        return llaveSha256;
////	    }
//}
