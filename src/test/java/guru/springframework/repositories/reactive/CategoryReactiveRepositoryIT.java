package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {
    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp(){
        this.categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveCategory(){
        Category category = new Category();
        category.setDescription("foo");
        categoryReactiveRepository.save(category).block();

        Long countOfCategory = this.categoryReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), countOfCategory);
    }

    @Test
    public void testFindByDescription(){
        Category category = new Category();
        category.setDescription("foo");
        categoryReactiveRepository.save(category).block();
        Category fooCat = this.categoryReactiveRepository.findByDescription("foo").block();
        assertNotNull(fooCat);
    }
}
