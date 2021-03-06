package br.com.dmarin.testmaker.web.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

import br.com.dmarin.testmaker.web.util.LinkUtil;

@Controller
public class RootController {

    public RootController() {
        super();
    }

    // API

    // discover

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void adminRoot(final HttpServletRequest request, final HttpServletResponse response) {
        final String rootUri = request.getRequestURL().toString();

        final URI questionGroupUri = new UriTemplate("{rootUri}/{resource}").expand(rootUri, "questionGroup");
        final String linkToQuestionGroup = LinkUtil.createLinkHeader(questionGroupUri.toASCIIString(), "collection");
        response.addHeader("Link", linkToQuestionGroup);
    }

}
