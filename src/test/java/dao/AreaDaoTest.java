package dao;

import static org.junit.Assert.assertEquals;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chaucer.o2o.BaseTest;
import com.chaucer.o2o.dao.AreaDao;
import com.chaucer.o2o.entity.Area;

public class AreaDaoTest extends BaseTest{
    @Autowired
    private  AreaDao areaDao;
    @Test
    public void queryArea() {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2,areaList.size());
    }

 
//
//    @Test
//    @Ignore
//    public void insertArea() {
//        Area area = new Area();
//        area.setAreaName("南苑");
//        area.setPriority(1);
//        int effectNumber = areaDao.insertArea(area);
//        assertEquals(1,effectNumber);
//    }
//
//    @Test
//    @Ignore
//    public void updateArea() throws ParseException {
//        Area area = new Area();
//        area.setAreaName("西苑");
//        area.setAreaId(3);
//        Date date = new Date();
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        String d = sdf.format(date);
////        date = sdf.parse(d);
//
//        area.setUpdateTime(date);
//        int effectNumber = areaDao.updateArea(area);
//        assertEquals(1,effectNumber);
//    }
//
//    @Test
//@Ignore
//    public void deleteArea() {
//        int effectNumber = areaDao.deleteArea(3);
//        assertEquals(1,effectNumber);
//
//    }


}