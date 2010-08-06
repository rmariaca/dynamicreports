/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
 * http://dynamicreports.sourceforge.net
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.dynamicreports.report.builder.datatype;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class DataTypes {
	private static final BigDecimalType bigDecimalType = new BigDecimalType();
	private static final BigIntegerType bigIntegerType = new BigIntegerType();	
	private static final ByteType byteType = new ByteType();
	private static final DoubleType doubleType = new DoubleType();
	private static final FloatType floatType = new FloatType();	
	private static final IntegerType integerType = new IntegerType();
	private static final LongType longType = new LongType();
	private static final ShortType shortType = new ShortType();
	private static final DateType dateType = new DateType();	
	private static final DateYearToMonthType dateYearToMonthType = new DateYearToMonthType();
	private static final DateYearToHourType dateYearToHourType = new DateYearToHourType();
	private static final DateYearToMinuteType dateYearToMinuteType = new DateYearToMinuteType();	
	private static final DateYearToSecondType dateYearToSecondType = new DateYearToSecondType();
	private static final DateYearToFractionType dateYearToFractionType = new DateYearToFractionType();
	private static final TimeHourToMinuteType timeHourToMinuteType = new TimeHourToMinuteType();
	private static final TimeHourToSecondType timeHourToSecondType = new TimeHourToSecondType();
	private static final TimeHourToFractionType timeHourToFractionType = new TimeHourToFractionType();	
	private static final PercentageType percentageType = new PercentageType();
	private static final BooleanType booleanType = new BooleanType();
	private static final CharacterType characterType = new CharacterType();
	private static final StringType stringType = new StringType();
	
	public static BigDecimalType bigDecimalType() {
		return bigDecimalType;
	}
	
	public static BigIntegerType bigIntegerType() {
		return bigIntegerType;
	}
	
	public static BooleanType booleanType() {
		return booleanType;
	}
	
	public static ByteType byteType() {
		return byteType;
	}
	
	public static DateType dateType() {
		return dateType;
	}
	
	public static DateYearToFractionType dateYearToFractionType() {
		return dateYearToFractionType;
	}
	
	public static DateYearToHourType dateYearToHourType() {
		return dateYearToHourType;
	}
	
	public static DateYearToMinuteType dateYearToMinuteType() {
		return dateYearToMinuteType;
	}
	
	public static DateYearToMonthType dateYearToMonthType() {
		return dateYearToMonthType;
	}
	
	public static DateYearToSecondType dateYearToSecondType() {
		return dateYearToSecondType;
	}
	
	public static DoubleType doubleType() {
		return doubleType;
	}
	
	public static FloatType floatType() {
		return floatType;
	}
	
	public static CharacterType characterType() {
		return characterType;
	}
	
	public static IntegerType integerType() {
		return integerType;
	}
	
	public static LongType longType() {
		return longType;
	}
	
	public static ShortType shortType() {
		return shortType;
	}
	
	public static StringType stringType() {
		return stringType;
	}
	
	public static TimeHourToFractionType timeHourToFractionType() {
		return timeHourToFractionType;
	}
	
	public static TimeHourToMinuteType timeHourToMinuteType() {
		return timeHourToMinuteType;
	}
	
	public static TimeHourToSecondType timeHourToSecondType() {
		return timeHourToSecondType;
	}	
	
	public static PercentageType percentageType() {
		return percentageType;
	}	
}
