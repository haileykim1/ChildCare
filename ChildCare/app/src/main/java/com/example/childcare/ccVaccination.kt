package com.example.childcare

data class ccVaccination (
    val locationName:String?, //시군명 : SIGUN_NM
    val facName:String?, //시설명 : FACLT_NM
    val telNo:String?, //전화번호 : TELNO
    val lAddr:String?, //지번주소 : REFINE_LOTNO_ADDR
    val rAddr:String?, //도로명주소 : REFINE_ROADNM_ADDR
    val la:String?, //위도 : REFINE_WGS84_LAT
    val lo:String? //경도 : REFINE_WGS84_LOGT
)