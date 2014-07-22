package br.com.dmarin.testmaker.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dmarin.testmaker.persistence.model.Question;
import br.com.dmarin.testmaker.persistence.service.IQuestionService;
import br.com.dmarin.testmaker.web.exception.MyResourceNotFoundException;
import br.com.dmarin.testmaker.web.hateoas.event.PaginatedResultsRetrievedEvent;
import br.com.dmarin.testmaker.web.hateoas.event.ResourceCreatedEvent;
import br.com.dmarin.testmaker.web.hateoas.event.SingleResourceRetrievedEvent;
import br.com.dmarin.testmaker.web.util.ResponseDeleteObject;
import br.com.dmarin.testmaker.web.util.ResponseRestObject;
import br.com.dmarin.testmaker.web.util.RestPreconditions;

import com.google.common.base.Preconditions;

@Controller
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private IQuestionService service;

    public QuestionController() {
        super();
    }

    // API

    // read - one

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Question findById(@PathVariable("id") final Long id, final HttpServletResponse response) {
        final Question resourceById = RestPreconditions.checkFound(service.findOne(id));

        eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
        return resourceById;
    }

    // read - all

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Question> findAll() {
        return service.findAll();
    }

    @RequestMapping(params = { "page", "start", "limit" }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseRestObject<Question> findPaginated(@RequestParam("page") final int page, @RequestParam("start") final int start, @RequestParam("limit") final int limit, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final Page<Question> resultPage = service.findPaginated(page - 1, limit);
        if (page > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<Question>(Question.class, uriBuilder, response, page, resultPage.getTotalPages(), limit));

        return new ResponseRestObject<Question>(true, resultPage.getContent(), resultPage.getTotalElements());
    }

    // write

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseRestObject<Question> create(@RequestBody final Question resource, final HttpServletResponse response) {
        Preconditions.checkNotNull(resource);
        final Question objCreated = service.create(resource);
        final Long idOfCreatedResource = objCreated.getId();

        eventPublisher.publishEvent(new ResourceCreatedEvent(this, response, idOfCreatedResource));
        return new ResponseRestObject<Question>(true, objCreated);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody final Question resource) {
        Preconditions.checkNotNull(resource);
        RestPreconditions.checkFound(service.findOne(resource.getId()));
        service.update(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseDeleteObject<Question> delete(@PathVariable("id") final Long id) {
        service.deleteById(id);
        return new ResponseDeleteObject<Question>(true);
    }

}
