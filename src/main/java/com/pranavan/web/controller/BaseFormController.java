package com.pranavan.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BaseFormController implements ServletContextAware {
	    public static final String MESSAGES_KEY = "successMessages";
	    public static final String ERRORS_MESSAGES_KEY = "errors";
	    protected final transient Log log = LogFactory.getLog(getClass());
	    




		private MessageSourceAccessor messages;
	    private ServletContext servletContext;

	public ServletContext getServletContext() {
			return servletContext;
		}



		@Autowired
	    public void setMessages(MessageSource messageSource) {
	        messages = new MessageSourceAccessor(messageSource);
	    }



	    public MessageSourceAccessor getMessages() {
	        return messages;
	    }


	    public void saveError(HttpServletRequest request, String error) {
	        List errors = (List) request.getSession().getAttribute(ERRORS_MESSAGES_KEY);
	        if (errors == null) {
	            errors = new ArrayList();
	        }
	        errors.add(error);
	        request.getSession().setAttribute(ERRORS_MESSAGES_KEY, errors);
	    }
	    

	    public void saveMessage(HttpServletRequest request, String msg) {
	        List messages = (List) request.getSession().getAttribute(MESSAGES_KEY);

	        if (messages == null) {
	            messages = new ArrayList();
	        }

	        messages.add(msg);
	        request.getSession().setAttribute(MESSAGES_KEY, messages);
	    }


	    public String getText(String msgKey, Locale locale) {
	        return messages.getMessage(msgKey, locale);
	    }

	    /**
	     * Convenient method for getting a i18n key's value with a single
	     * string argument.
	     *
	     * @param msgKey .
	     * @param arg .
	     * @param locale the current locale
	     * @return .
	     */
	    public String getText(String msgKey, String arg, Locale locale) {
	        return getText(msgKey, new Object[] { arg }, locale);
	    }

	    /**
	     * Convenience method for getting a i18n key's value with arguments.
	     *
	     * @param msgKey .
	     * @param args .
	     * @param locale the current locale .
	     * @return .
	     */
	    public String getText(String msgKey, Object[] args, Locale locale) {
	        return messages.getMessage(msgKey, args, locale);
	    }

	  


	   public void setServletContext(ServletContext servletContext) {
	        this.servletContext = servletContext;
			
		}
		  /**
	     * Set up a custom property editor for converting form inputs to real objects
	     * @param request the current request
	     * @param binder the data binder
	     */
	    @InitBinder
	    protected void initBinder(HttpServletRequest request,
	                              ServletRequestDataBinder binder) {
	        //binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
	       
	    }
	    /**
	     * Convenience method to get the Configuration HashMap
	     * from the servlet context.
	     *
	     * @return the user's populated form from the session
	     */
	    

	   

	   	}


