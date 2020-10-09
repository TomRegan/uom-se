/*
 * Units of Measurement Implementation for Java SE
 * Copyright (c) 2005-2020, Jean-Marie Dautelle, Werner Keil, Otavio Santana.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tec.uom.se.quantity;

import static org.junit.Assert.assertEquals;
import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.ElectricResistance;
import javax.measure.quantity.Length;
import javax.measure.quantity.Time;

import org.junit.Assert;
import org.junit.Test;

import tec.uom.se.quantity.Quantities;
import tec.uom.se.unit.Units;

public class LongQuantityTest {

  private static final Unit<?> SQUARE_OHM = Units.OHM.multiply(Units.OHM);
  private final LongQuantity<ElectricResistance> TWO_OHM = createQuantity(2L, Units.OHM);

  private <Q extends Quantity<Q>> LongQuantity<Q> createQuantity(long l, Unit<Q> unit) {
    return new LongQuantity<>(Long.valueOf(l).longValue(), unit);
  }

  @Test
  public void divideTest() {
    LongQuantity<ElectricResistance> quantity1 = new LongQuantity<>(Long.valueOf(3).longValue(), Units.OHM);
    LongQuantity<ElectricResistance> quantity2 = new LongQuantity<>(Long.valueOf(2).longValue(), Units.OHM);
    Quantity<?> result = quantity1.divide(quantity2);
    assertEquals(Double.valueOf(1.5d), result.getValue());
  }

  @Test
  public void addTest() {
    LongQuantity quantity1 = new LongQuantity(Long.valueOf(1).longValue(), Units.OHM);
    LongQuantity quantity2 = new LongQuantity(Long.valueOf(2).longValue(), Units.OHM);
    Quantity<ElectricResistance> result = quantity1.add(quantity2);
    assertEquals(Short.valueOf("3").longValue(), result.getValue().longValue());
  }

  @Test
  public void subtractTest() {
    LongQuantity<ElectricResistance> quantity1 = new LongQuantity<>(Long.valueOf(1).longValue(), Units.OHM);
    LongQuantity<ElectricResistance> quantity2 = new LongQuantity<>(Long.valueOf(2).longValue(), Units.OHM);
    Quantity<ElectricResistance> result = quantity2.subtract(quantity1);
    assertEquals(Short.valueOf("1").longValue(), result.getValue().longValue());
    assertEquals(Units.OHM, result.getUnit());
  }

  /**
   * Verifies that multiplication multiplies correctly.
   */
  @Test
  public void multiplicationMultipliesCorrectlyWithSameUnitsWithoutMultiples() {
    Quantity<?> actual = TWO_OHM.multiply(TWO_OHM);
    LongQuantity<?> expected = createQuantity(4L, SQUARE_OHM);
    assertEquals(expected, actual);
  }

  @Test
  public void longValueTest() {
    LongQuantity<Time> day = new LongQuantity<>(Double.valueOf(3).longValue(), Units.DAY);
    long hours = day.longValue(Units.HOUR);
    assertEquals(72L, hours);
  }

  @Test
  public void testEquality() throws Exception {
    Quantity<Length> value = Quantities.getQuantity(new Long(10), Units.METRE);
    Quantity<Length> anotherValue = Quantities.getQuantity(new Long(10), Units.METRE);
    assertEquals(value, anotherValue);
  }

  @Test
  public void toTest() {
    Quantity<Time> day = Quantities.getQuantity(1D, Units.DAY);
    Quantity<Time> hour = day.to(Units.HOUR);
    Assert.assertEquals(hour.getValue().intValue(), 24);
    Assert.assertEquals(hour.getUnit(), Units.HOUR);

    Quantity<Time> dayResult = hour.to(Units.DAY);
    Assert.assertEquals(dayResult.getValue().intValue(), day.getValue().intValue());
    Assert.assertEquals(dayResult.getValue().intValue(), day.getValue().intValue());
  }
}
