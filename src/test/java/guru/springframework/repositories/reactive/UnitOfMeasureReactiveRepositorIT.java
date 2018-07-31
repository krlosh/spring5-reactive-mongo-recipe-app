package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
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
public class UnitOfMeasureReactiveRepositorIT {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp(){
        this.unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testOUMSave() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("dummy");
        this.unitOfMeasureReactiveRepository.save(unitOfMeasure).then().block();

        Long countOfRecipe = this.unitOfMeasureReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), countOfRecipe);
        this.unitOfMeasureReactiveRepository.save(unitOfMeasure).then().block();
    }

    @Test
    public void testFindByDescription(){
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("dummy");
        this.unitOfMeasureReactiveRepository.save(unitOfMeasure).then().block();
        this.unitOfMeasureReactiveRepository.save(unitOfMeasure).then().block();
        UnitOfMeasure dummyUOM = this.unitOfMeasureReactiveRepository.findByDescription("dummy").block();

        assertNotNull(dummyUOM);
    }
}
