package test.services;

import com.yammer.dropwizard.testing.ResourceTest;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import persistence.firstfilter.cmmi.dao.*;
import persistence.firstfilter.cmmi.model.Standard;
import persistence.firstfilter.cmmi.model.WorkProduct;
import services.cmmi.CmmiService;
import services.cmmi.CmmiServiceImp;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CmmiServiceImpTest{

    private StandardDao standardDao;
    private ProcessDao processDao;
    private SpecificGoalDao specificGoalDao;
    private SpecificPracticeDao specificPracticeDao;
    private WorkProductDao workProductDao;
    private CmmiService cmmiService;

    @Before
    public void setUp() throws Exception {
        standardDao = mock(StandardDao.class);
        processDao = mock(ProcessDao.class);
        specificGoalDao = mock(SpecificGoalDao.class);
        specificPracticeDao = mock(SpecificPracticeDao.class);
        workProductDao = mock(WorkProductDao.class);

        cmmiService = new CmmiServiceImp(standardDao, specificGoalDao, processDao, specificPracticeDao, workProductDao);
    }

    @Test
    public void testCreateStandard(){
        Standard standard = new Standard();
        when(standardDao.create(standard)).thenReturn(123);

        int id = cmmiService.createStandard(standard);
        verify(standardDao).create(standard);
        assert id == 123;
    }

    @Test
    public void testCreateStandardWithName(){
        String nameTestStandard = "nameTestStandard";
        when(standardDao.create(nameTestStandard)).thenReturn(123);

        int id = cmmiService.createStandard(nameTestStandard);
        verify(standardDao).create(nameTestStandard);
        assert id == 123;
    }

    @Test
    public void testReadStandard() {
        Standard standard = new Standard();
        when(standardDao.read(123)).thenReturn(standard);

        Standard standardReturned = cmmiService.readStandard(123);
        verify(standardDao).read(123);
        assert standard.equals(standardReturned);
    }

    public void testDeleteStandard() throws Exception {
        cmmiService.deleteStandard(123);
        verify(standardDao).delete(123);
    }

    public void testReadAllStandards() throws Exception {

    }

    public void testCreateProcess() throws Exception {

    }

    public void testReadProcess() throws Exception {

    }

    public void testReadAllProcessByStandard() throws Exception {

    }

    public void testCreateSpecificGoal() throws Exception {

    }

    public void testReadSpecificGoal() throws Exception {

    }

    public void testReadAllSpecificGoalByProcess() throws Exception {

    }

    public void testCreateSpecificPractice() throws Exception {

    }

    public void testReadSpecificPractice() throws Exception {

    }

    public void testReadAllSpecificPracticeBySpecificGoal() throws Exception {

    }

    public void testCreateWorkProduct() throws Exception {

    }

    public void testReadWorkProduct() throws Exception {

    }

    public void testReadAllWorkProductBySpecificPractice() throws Exception {

    }


}