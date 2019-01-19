package viereckbestimmung_haiden;

import org.junit.Test;
import static org.junit.Assert.*;

public class ViereckbestimmungTest {

  Punkt[] quadrat1 = {new Punkt(0, 0), new Punkt(0, 1), new Punkt(1, 1), new Punkt(1, 0)};
  Punkt[] quadrat2 = {new Punkt(1, 1), new Punkt(-1, 3), new Punkt(1, 5), new Punkt(3, 3)};
  Punkt[] rechteck1 = {new Punkt(0, 0), new Punkt(0, 2), new Punkt(1, 2), new Punkt(1, 0)};
  Punkt[] rechteck2 = {new Punkt(1, 1), new Punkt(-2, 4), new Punkt(0, 6), new Punkt(3, 3)};
  Punkt[] pg1 = {new Punkt(1, 1), new Punkt(4, 4), new Punkt(4, -2), new Punkt(1, -5)};
  Punkt[] pg2 = {new Punkt(2, 1), new Punkt(8, 2), new Punkt(9, -1), new Punkt(3, -2)};
  Punkt[] raute1 = {new Punkt(2, 1), new Punkt(5, 5), new Punkt(8, 1), new Punkt(5, -3)};
  Punkt[] raute2 = {new Punkt(3, 3), new Punkt(6, 7), new Punkt(9, 3), new Punkt(6, -1)};
  Punkt[] deltoid1 = {new Punkt(3, 8), new Punkt(5, 8), new Punkt(9, 2), new Punkt(3, 6)};
  Punkt[] deltoid2 = {new Punkt(3, 2), new Punkt(1, 7), new Punkt(6, 5), new Punkt(6, 2)};
  Punkt[] allg1 = {new Punkt(2, 3), new Punkt(3, 5), new Punkt(1, 6), new Punkt(4, 3)};
  Punkt[] allg2 = {new Punkt(3, 4), new Punkt(1, 5), new Punkt(3, 9), new Punkt(4, 3)};
  Punkt[] punkt = {new Punkt(0, 0), new Punkt(0, 0), new Punkt(0, 0), new Punkt(0, 0)};
  Punkt[] strich = {new Punkt(0, 0), new Punkt(0, 0), new Punkt(2, 2), new Punkt(2, 2)};
  Punkt[] dreieck = {new Punkt(0, 0), new Punkt(0, 0), new Punkt(2, 2), new Punkt(2, 0)};
  Punkt[][] arr = {quadrat1, quadrat2, rechteck1, rechteck2, pg1, pg2, raute1, raute2, deltoid1, deltoid2, allg1, allg2};

  @Test
  public void testIsViereck() {
    assertFalse(Viereckbestimmung.isViereck(punkt));
    assertFalse(Viereckbestimmung.isViereck(strich));
    assertFalse(Viereckbestimmung.isViereck(dreieck));
    for (Punkt[] p : arr) {
      assertTrue(Viereckbestimmung.isViereck(p));
    }
  }

  @Test
  public void testIsQuadrat() {
    assertFalse(Viereckbestimmung.isQuadrat(punkt));
    assertFalse(Viereckbestimmung.isQuadrat(strich));
    assertFalse(Viereckbestimmung.isQuadrat(dreieck));
    for (Punkt[] p : arr) {
      if (p == quadrat1 || p == quadrat2) {
        assertTrue(Viereckbestimmung.isQuadrat(p));
      } else {
        assertFalse(Viereckbestimmung.isQuadrat(p));
      }
    }
  }

  @Test
  public void testIsRechteck() {
    assertFalse(Viereckbestimmung.isRechteck(punkt));
    assertFalse(Viereckbestimmung.isRechteck(strich));
    assertFalse(Viereckbestimmung.isRechteck(dreieck));
    for (Punkt[] p : arr) {
      if (p == quadrat1 || p == quadrat2 || p == rechteck1 || p == rechteck2) {
        assertTrue(Viereckbestimmung.isRechteck(p));
      } else {
        assertFalse(Viereckbestimmung.isRechteck(p));
      }
    }
  }

  @Test
  public void testIsParallelogramm() {
    assertFalse(Viereckbestimmung.isParallelogramm(punkt));
    assertFalse(Viereckbestimmung.isParallelogramm(strich));
    assertFalse(Viereckbestimmung.isParallelogramm(dreieck));
    for (Punkt[] p : arr) {
      if (p == deltoid1 || p == deltoid2 || p == allg1 || p == allg2) {
        assertFalse(Viereckbestimmung.isParallelogramm(p));
      } else {
        assertTrue(Viereckbestimmung.isParallelogramm(p));
      }
    }
  }

  @Test
  public void testIsRaute() {
    assertFalse(Viereckbestimmung.isRaute(punkt));
    assertFalse(Viereckbestimmung.isRaute(strich));
    assertFalse(Viereckbestimmung.isRaute(dreieck));
    for (Punkt[] p : arr) {
      if (p == quadrat1 || p == quadrat2 || p == raute1 || p == raute2) {
        assertTrue(Viereckbestimmung.isRaute(p));
      } else {
        assertFalse(Viereckbestimmung.isRaute(p));
      }
    }
  }

  @Test
  public void testIsDeltoid() {
    assertFalse(Viereckbestimmung.isDeltoid(punkt));
    assertFalse(Viereckbestimmung.isDeltoid(strich));
    assertFalse(Viereckbestimmung.isDeltoid(dreieck));
    for (Punkt[] p : arr) {
      if (p == rechteck1 || p == rechteck2 || p == pg1 || p == pg2 || p == allg1 || p == allg2) {
        assertFalse(Viereckbestimmung.isDeltoid(p));
      } else {
        assertTrue(Viereckbestimmung.isDeltoid(p));
      }
    }
  }

  @Test
  public void testDistanz() {
    Punkt p = new Punkt(1, 1);
    Punkt q = new Punkt(4, 5);
    double expResult = 5;
    assertEquals(expResult, Viereckbestimmung.distanz(p, q), 0);
    q = new Punkt(2, 2);
    expResult = Math.sqrt(2);
    assertEquals(expResult, Viereckbestimmung.distanz(p, q), 0.00001);
    q = new Punkt(1, 1);
    expResult = 0;
    assertEquals(expResult, Viereckbestimmung.distanz(p, q), 0);
  }

  @Test
  public void testIsEqual1() {
    double pi = 3.1415;
    assertTrue(Viereckbestimmung.isEqual(1e-4, Math.PI, pi));
    assertFalse(Viereckbestimmung.isEqual(1e-5, Math.PI, pi));
    assertTrue(Viereckbestimmung.isEqual(1e-4, pi, Math.PI));
    assertFalse(Viereckbestimmung.isEqual(1e-5, pi, Math.PI));
    assertTrue(Viereckbestimmung.isEqual(0, 3, 3));
    assertFalse(Viereckbestimmung.isEqual(-1e-8, 2, 2));
  }

  @Test
  public void testIsEqual2() {
    assertFalse(Viereckbestimmung.isEqual(42));
    assertFalse(Viereckbestimmung.isEqual(42, 42));
    assertTrue(Viereckbestimmung.isEqual(1e-4, 3.1415, 3.14159, Math.PI));
    assertFalse(Viereckbestimmung.isEqual(1e-5, 3.1415, 3.14159, Math.PI));
  }

  @Test
  public void nullTest() {
    assertFalse(Viereckbestimmung.isViereck(null));
    assertFalse(Viereckbestimmung.isDeltoid(null));
    assertFalse(Viereckbestimmung.isParallelogramm(null));
    assertFalse(Viereckbestimmung.isQuadrat(null));
    assertFalse(Viereckbestimmung.isRaute(null));
    assertFalse(Viereckbestimmung.isRechteck(null));
    Punkt[] p = {new Punkt(0, 0), new Punkt(0, 1), new Punkt(1, 1), null};
    assertFalse(Viereckbestimmung.isViereck(p));
    assertFalse(Viereckbestimmung.isDeltoid(p));
    assertFalse(Viereckbestimmung.isParallelogramm(p));
    assertFalse(Viereckbestimmung.isQuadrat(p));
    assertFalse(Viereckbestimmung.isRaute(p));
    assertFalse(Viereckbestimmung.isRechteck(p));
    p[2] = null;
    assertFalse(Viereckbestimmung.isViereck(p));
    assertFalse(Viereckbestimmung.isDeltoid(p));
    assertFalse(Viereckbestimmung.isParallelogramm(p));
    assertFalse(Viereckbestimmung.isQuadrat(p));
    assertFalse(Viereckbestimmung.isRaute(p));
    assertFalse(Viereckbestimmung.isRechteck(p));
    p[1] = null;
    assertFalse(Viereckbestimmung.isViereck(p));
    assertFalse(Viereckbestimmung.isDeltoid(p));
    assertFalse(Viereckbestimmung.isParallelogramm(p));
    assertFalse(Viereckbestimmung.isQuadrat(p));
    assertFalse(Viereckbestimmung.isRaute(p));
    assertFalse(Viereckbestimmung.isRechteck(p));
    p[0] = null;
    assertFalse(Viereckbestimmung.isViereck(p));
    assertFalse(Viereckbestimmung.isDeltoid(p));
    assertFalse(Viereckbestimmung.isParallelogramm(p));
    assertFalse(Viereckbestimmung.isQuadrat(p));
    assertFalse(Viereckbestimmung.isRaute(p));
    assertFalse(Viereckbestimmung.isRechteck(p));
  }
}
