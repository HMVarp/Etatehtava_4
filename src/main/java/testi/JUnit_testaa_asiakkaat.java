package testi;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import model.Myynti;
import model.dao.Dao;

@TestMethodOrder(OrderAnnotation.class)
class JUnit_testaa_asiakkaat {
	
	@Test
	@Order(1) 
	public void testPoistaKaikkiAsiakkaat() {
		Dao dao = new Dao();
		dao.removeAllItems("Salasana");
		ArrayList<Myynti> myynnit = dao.getAllItems();
		assertEquals(0, myynnit.size());		
	}
	
	@Test
	@Order(2) 
	public void testLisaaAsiakkaat() {
		Dao dao = new Dao();
		Myynti asiakas_1 = new Myynti(0, "Elli", "Etana", "0500010101", "etana.e@google.com");
		Myynti asiakas_2 = new Myynti(0, "Alfred", "Quack", "0409898989", "al.qua@google.com");
		Myynti asiakas_3 = new Myynti(0, "Heikki", "Hippa", "0500050055", "hh@hotmail.com");
		Myynti asiakas_4 = new Myynti(0, "Paavo", "Pesusieni", "0409898989", "pape@google.com");
		assertEquals(true, dao.addItem(asiakas_1));	
		assertEquals(true, dao.addItem(asiakas_2));
		assertEquals(true, dao.addItem(asiakas_3));
		assertEquals(true, dao.addItem(asiakas_4));
		assertEquals(4, dao.getAllItems().size());		
	}
	
	@Test
	@Order(3) 
	public void testMuutaAsiakas() {
		Dao dao = new Dao();		
		ArrayList<Myynti> myynnit = dao.getAllItems("Heikki");
		System.out.println(myynnit);
		myynnit.get(0).setEtunimi("Harri");		
		dao.changeItem(myynnit.get(0));
		myynnit = dao.getAllItems("Harri");
		assertEquals("Harri", myynnit.get(0).getEtunimi());
		assertEquals("Hippa", myynnit.get(0).getSukunimi());
		assertEquals("0500050055", myynnit.get(0).getPuhelin());
		assertEquals("hh@hotmail.com", myynnit.get(0).getSposti());		
	}
	
	@Test
	@Order(4) 
	public void testPoistaAsiakas() {
		Dao dao = new Dao();
		ArrayList<Myynti> myynnit = dao.getAllItems("Harri");
		dao.removeItem(myynnit.get(0).getAsiakas_id());
		assertEquals(0, dao.getAllItems("Harri").size());					
	}
	
	@Test
	@Order(5) 
	public void testHaeOlematonAsiakas() {
		Dao dao = new Dao();
		assertNull(dao.getItem(-1));
	}
}