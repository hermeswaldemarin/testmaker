package br.com.dmarin.testmaker.web;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import br.com.dmarin.testmaker.common.web.AbstractBasicLiveTest;
import br.com.dmarin.testmaker.model.QuestionGroup;
import br.com.dmarin.testmaker.spring.ConfigTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class }, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class FooLiveTest extends AbstractBasicLiveTest<QuestionGroup> {

    public FooLiveTest() {
        super(QuestionGroup.class);
    }

    // API

    @Override
    public final void create() {
        create(new QuestionGroup(randomAlphabetic(6)));
    }

    @Override
    public final String createAsUri() {
        return createAsUri(new QuestionGroup(randomAlphabetic(6)));
    }

}
