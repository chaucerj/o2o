package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chaucer.o2o.BaseTest;
import com.chaucer.o2o.dao.HeadLineDao;
import com.chaucer.o2o.entity.HeadLine;


public class HeadLineDaoTest extends BaseTest {
	@Autowired
	private HeadLineDao headLineDao;
   @Test
   public void testQueryHeadLine(){
	   List<HeadLine> headLine = headLineDao.queryHeadLine(new HeadLine());
	   assertEquals(4,headLine.size());
   }
}
