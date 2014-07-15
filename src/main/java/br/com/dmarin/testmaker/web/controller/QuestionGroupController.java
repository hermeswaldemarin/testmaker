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

import br.com.dmarin.testmaker.persistence.model.QuestionGroup;
import br.com.dmarin.testmaker.persistence.service.IQuestionGroupService;
import br.com.dmarin.testmaker.web.exception.MyResourceNotFoundException;
import br.com.dmarin.testmaker.web.hateoas.event.PaginatedResultsRetrievedEvent;
import br.com.dmarin.testmaker.web.hateoas.event.ResourceCreatedEvent;
import br.com.dmarin.testmaker.web.hateoas.event.SingleResourceRetrievedEvent;
import br.com.dmarin.testmaker.web.util.RestPreconditions;

import com.google.common.base.Preconditions;

@Controller
@RequestMapping(value = "/questiongroups")
public class QuestionGroupController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private IQuestionGroupService service;

    public QuestionGroupController() {
        super();
    }

    // API

    // read - one

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public QuestionGroup findById(@PathVariable("id") final Long id, final HttpServletResponse response) {
        final QuestionGroup resourceById = RestPreconditions.checkFound(service.findOne(id));

        eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
        return resourceById;
    }

    // read - all

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<QuestionGroup> findAll() {
        return service.findAll();
    }

    @RequestMapping(params = { "page", "size" }, method = RequestMethod.GET)
    @ResponseBody
    public List<QuestionGroup> findPaginated(@RequestParam("page") final int page, @RequestParam("size") final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final Page<QuestionGroup> resultPage = service.findPaginated(page, size);
        if (page > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<QuestionGroup>(QuestionGroup.class, uriBuilder, response, page, resultPage.getTotalPages(), size));

        return resultPage.getContent();
    }

    // write

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final QuestionGroup resource, final HttpServletResponse response) {
        Preconditions.checkNotNull(resource);
        final Long idOfCreatedResource = service.create(resource).getId();

        eventPublisher.publishEvent(new ResourceCreatedEvent(this, response, idOfCreatedResource));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody final QuestionGroup resource) {
        Preconditions.checkNotNull(resource);
        RestPreconditions.checkFound(service.findOne(resource.getId()));
        service.update(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long id) {
        service.deleteById(id);
    }

}
