/*
 * Units of Measurement Implementation for Java SE
 * Copyright (c) 2005-2021, Jean-Marie Dautelle, Werner Keil, Otavio Santana.
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
package tec.uom.se;

import java.io.Serializable;

import javax.measure.Quantity;
import javax.measure.Unit;

/**
 * Unit specialized for the Java SE platform. It extends {@link Unit} with {@linkplain Comparable} and {@linkplain Serializable }
 * 
 * @see {@link Unit}
 * @author werner
 * @param <Q>
 * @version 1.1
 * @since 1.0.9
 */
public interface ComparableUnit<Q extends Quantity<Q>> extends Unit<Q>, Comparable<Unit<Q>>, Serializable {

  /**
   * Compares two instances of {@link Unit <Q>}, doing the conversion of unit if necessary.
   *
   * @param that
   *          the {@code Unit<Q>} to be compared with this instance.
   * @return {@code true} if {@code that < this}.
   * @throws NullPointerException
   *           if the unit is null
   */
  boolean isEquivalentTo(Unit<Q> that);

  /**
   * Indicates if this unit belongs to the set of coherent SI units (unscaled SI units).
   * 
   * The base and coherent derived units of the SI form a coherent set, designated the set of coherent SI units. The word coherent is used here in the
   * following sense: when coherent units are used, equations between the numerical values of quantities take exactly the same form as the equations
   * between the quantities themselves. Thus if only units from a coherent set are used, conversion factors between units are never required.
   * 
   * @return <code>equals(toSystemUnit())</code>
   */
  boolean isSystemUnit();
}
