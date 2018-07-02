@file:Suppress("unused")

package org.hoshino9.functional

/** A curring funtion that takes 0 argument */
fun <R> curring(f: () -> R): () -> R = f

/** A curring funtion that takes 1 argument */
fun <P0, R> curring(f: (P0) -> R): (P0) -> R = { p0 -> f(p0) }

/** A curring funtion that takes 2 arguments */
fun <P0, P1, R> curring(f: (P0, P1) -> R): (P0) -> (P1) -> R = { p0 -> { p1 -> f(p0, p1) } }

/** A curring funtion that takes 3 arguments */
fun <P0, P1, P2, R> curring(f: (P0, P1, P2) -> R): (P0) -> (P1) -> (P2) -> R = { p0 -> { p1 -> { p2 -> f(p0, p1, p2) } } }

/** A curring funtion that takes 4 arguments */
fun <P0, P1, P2, P3, R> curring(f: (P0, P1, P2, P3) -> R): (P0) -> (P1) -> (P2) -> (P3) -> R = { p0 -> { p1 -> { p2 -> { p3 -> f(p0, p1, p2, p3) } } } }

/** A curring funtion that takes 5 arguments */
fun <P0, P1, P2, P3, P4, R> curring(f: (P0, P1, P2, P3, P4) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> f(p0, p1, p2, p3, p4) } } } } }

/** A curring funtion that takes 6 arguments */
fun <P0, P1, P2, P3, P4, P5, R> curring(f: (P0, P1, P2, P3, P4, P5) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> f(p0, p1, p2, p3, p4, p5) } } } } } }

/** A curring funtion that takes 7 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, R> curring(f: (P0, P1, P2, P3, P4, P5, P6) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> f(p0, p1, p2, p3, p4, p5, p6) } } } } } } }

/** A curring funtion that takes 8 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> f(p0, p1, p2, p3, p4, p5, p6, p7) } } } } } } } }

/** A curring funtion that takes 9 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8) } } } } } } } } }

/** A curring funtion that takes 10 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> (P9) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> { p9 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9) } } } } } } } } } }

/** A curring funtion that takes 11 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> (P9) -> (P10) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> { p9 -> { p10 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) } } } } } } } } } } }

/** A curring funtion that takes 12 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> (P9) -> (P10) -> (P11) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> { p9 -> { p10 -> { p11 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11) } } } } } } } } } } } }

/** A curring funtion that takes 13 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> (P9) -> (P10) -> (P11) -> (P12) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> { p9 -> { p10 -> { p11 -> { p12 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12) } } } } } } } } } } } } }

/** A curring funtion that takes 14 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> (P9) -> (P10) -> (P11) -> (P12) -> (P13) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> { p9 -> { p10 -> { p11 -> { p12 -> { p13 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13) } } } } } } } } } } } } } }

/** A curring funtion that takes 15 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> (P9) -> (P10) -> (P11) -> (P12) -> (P13) -> (P14) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> { p9 -> { p10 -> { p11 -> { p12 -> { p13 -> { p14 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14) } } } } } } } } } } } } } } }

/** A curring funtion that takes 16 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> (P9) -> (P10) -> (P11) -> (P12) -> (P13) -> (P14) -> (P15) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> { p9 -> { p10 -> { p11 -> { p12 -> { p13 -> { p14 -> { p15 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15) } } } } } } } } } } } } } } } }

/** A curring funtion that takes 17 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> (P9) -> (P10) -> (P11) -> (P12) -> (P13) -> (P14) -> (P15) -> (P16) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> { p9 -> { p10 -> { p11 -> { p12 -> { p13 -> { p14 -> { p15 -> { p16 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16) } } } } } } } } } } } } } } } } }

/** A curring funtion that takes 18 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> (P9) -> (P10) -> (P11) -> (P12) -> (P13) -> (P14) -> (P15) -> (P16) -> (P17) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> { p9 -> { p10 -> { p11 -> { p12 -> { p13 -> { p14 -> { p15 -> { p16 -> { p17 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17) } } } } } } } } } } } } } } } } } }

/** A curring funtion that takes 19 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> (P9) -> (P10) -> (P11) -> (P12) -> (P13) -> (P14) -> (P15) -> (P16) -> (P17) -> (P18) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> { p9 -> { p10 -> { p11 -> { p12 -> { p13 -> { p14 -> { p15 -> { p16 -> { p17 -> { p18 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18) } } } } } } } } } } } } } } } } } } }

/** A curring funtion that takes 20 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> (P9) -> (P10) -> (P11) -> (P12) -> (P13) -> (P14) -> (P15) -> (P16) -> (P17) -> (P18) -> (P19) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> { p9 -> { p10 -> { p11 -> { p12 -> { p13 -> { p14 -> { p15 -> { p16 -> { p17 -> { p18 -> { p19 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19) } } } } } } } } } } } } } } } } } } } }

/** A curring funtion that takes 21 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> (P9) -> (P10) -> (P11) -> (P12) -> (P13) -> (P14) -> (P15) -> (P16) -> (P17) -> (P18) -> (P19) -> (P20) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> { p9 -> { p10 -> { p11 -> { p12 -> { p13 -> { p14 -> { p15 -> { p16 -> { p17 -> { p18 -> { p19 -> { p20 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20) } } } } } } } } } } } } } } } } } } } } }

/** A curring funtion that takes 22 arguments */
fun <P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, R> curring(f: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21) -> R): (P0) -> (P1) -> (P2) -> (P3) -> (P4) -> (P5) -> (P6) -> (P7) -> (P8) -> (P9) -> (P10) -> (P11) -> (P12) -> (P13) -> (P14) -> (P15) -> (P16) -> (P17) -> (P18) -> (P19) -> (P20) -> (P21) -> R = { p0 -> { p1 -> { p2 -> { p3 -> { p4 -> { p5 -> { p6 -> { p7 -> { p8 -> { p9 -> { p10 -> { p11 -> { p12 -> { p13 -> { p14 -> { p15 -> { p16 -> { p17 -> { p18 -> { p19 -> { p20 -> { p21 -> f(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21) } } } } } } } } } } } } } } } } } } } } } }

