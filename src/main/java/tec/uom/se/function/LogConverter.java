/*
 * Units of Measurement Implementation for Java SE
 * Copyright (c) 2005-2018, Jean-Marie Dautelle, Werner Keil, Otavio Santana.
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
package tec.uom.se.function;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

import tec.uom.lib.common.function.ValueSupplier;
import tec.uom.se.AbstractConverter;

/**
 * <p>
 * This class represents a logarithmic converter of limited precision. Such converter is typically used to create logarithmic unit. For example:[code]
 * Unit<Dimensionless> BEL = Unit.ONE.transform(new LogConverter(10).inverse()); [/code]
 * </p>
 *
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 1.0, October 10, 2016
 */
public final class LogConverter extends AbstractConverter implements ValueSupplier<String> { // implements Immutable<String> {

  /**
	 * 
	 */
  private static final long serialVersionUID = -7584688290961460870L;

  /**
   * Holds the logarithmic base.
   */
  private final double base;
  /**
   * Holds the natural logarithm of the base.
   */
  private final double logOfBase;

  /**
   * Returns a logarithmic converter having the specified base.
   *
   * @param base
   *          the logarithmic base (e.g. <code>Math.E</code> for the Natural Logarithm).
   */
  public LogConverter(double base) {
    this.base = base;
    this.logOfBase = Math.log(base);
  }

  /**
   * Returns the logarithmic base of this converter.
   *
   * @return the logarithmic base (e.g. <code>Math.E</code> for the Natural Logarithm).
   */
  public double getBase() {
    return base;
  }

  @Override
  public AbstractConverter inverse() {
    return new ExpConverter(base);
  }

  @Override
  public final String toString() {
    if (base == Math.E) {
      return "ln";
    } else {
      return "Log(" + base + ")";
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof LogConverter) {
      LogConverter that = (LogConverter) obj;
      return Objects.equals(base, that.base);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(base);
  }

  @Override
  public double convert(double amount) {
    return Math.log(amount) / logOfBase;
  }

  @Override
  public BigDecimal convert(BigDecimal value, MathContext ctx) throws ArithmeticException {
    return BigDecimal.valueOf(convert(value.doubleValue())); // Reverts to
    // double
    // conversion.
  }

  @Override
  public boolean isLinear() {
    return false;
  }

  @Override
  public String getValue() {
    return toString();
  }
}
