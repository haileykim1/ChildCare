package com.example.childcare

import com.google.gson.annotations.SerializedName

data class ccVaccination (
    @SerializedName("SIGUN_NM")val SIGUN_NM:String?, //시군명 : SIGUN_NM
    @SerializedName("SIGUN_CD")val SIGUN_CD:String?, //시군코드
    @SerializedName("FACLT_NM")val FACLT_NM:String?, //시설명 : FACLT_NM
    @SerializedName("TELNO")val TELNO:String?, //전화번호 : TELNO
    @SerializedName("DATA_STD_DE")val DATA_STD_DE:String?, // 데이터기준일자
    @SerializedName("REFINE_ZIP_CD")val REFINE_ZIP_CD:String?, //소재지 우편번호
    @SerializedName("REFINE_LOTNO_ADDR")val REFINE_LOTNO_ADDR:String?, //지번주소 : REFINE_LOTNO_ADDR
    @SerializedName("REFINE_ROADNM_ADDR")val REFINE_ROADNM_ADDR:String?, //도로명주소 : REFINE_ROADNM_ADDR
    @SerializedName("APPONT_DE")val APPONT_DE:String?, //지정일자
    @SerializedName("REFINE_WGS84_LAT")val REFINE_WGS84_LAT:String?, //위도 : REFINE_WGS84_LAT
    @SerializedName("REFINE_WGS84_LOGT")val REFINE_WGS84_LOGT:String? //경도 : REFINE_WGS84_LOGT
)