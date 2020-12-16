package com.example.childcare

import com.google.gson.annotations.SerializedName

data class ccFacility (
    @SerializedName("KIDGARTN_NM")val KIDGARTN_NM:String?, //어린이집명
    @SerializedName("SIDO_NM")val SIDO_NM:String?, //시도명
    @SerializedName("SIGNGU_NM")val SIGNGU_NM:String?, //시군구명
    @SerializedName("KIDGARTN_DIV_NM")val KIDGARTN_DIV_NM:String?, //어린이집유형구분
    @SerializedName("PSN_CAPA_CNT")val PSN_CAPA_CNT:String?, //정원수
    @SerializedName("CHLDCARE_SCHLSTAF_CNT")val CHLDCARE_SCHLSTAF_CNT:String?, //보육교직원수
    @SerializedName("REFINE_ROADNM_ADDR")val REFINE_ROADNM_ADDR:String?, //소재지도로명주소
    @SerializedName("KIDGARTN_TELNO")val KIDGARTN_TELNO:String?, //어린이집전화번호
    @SerializedName("KIDGARTN_FAXNO")val KIDGARTN_FAXNO:String?, //어린이집팩스번호
    @SerializedName("CHLDCR_ROOM_CNT")val CHLDCR_ROOM_CNT:String?, //보육실수
    @SerializedName("PLAYGRND_CNT")val PLAYGRND_CNT:String?, //놀이터수
    @SerializedName("CCTV_INSTL_CNT")val CCTV_INSTL_CNT:String?, //CCTV설치수
    @SerializedName("ATNDSKL_VEHCLE_OPERT_YN")val ATNDSKL_VEHCLE_OPERT_YN:String?, //통학차량운영여부
    @SerializedName("HMPG_ADDR")val HMPG_ADDR:String?, //홈페이지주소
    @SerializedName("DATA_STD_DE")val DATA_STD_DE:String?, //데이터기준일자
    @SerializedName("SIGUN_NM")val SIGUN_NM:String?, //시군명
    @SerializedName("SIGUN_CD")val SIGUN_CD:String?, //시군코드
    @SerializedName("REFINE_ZIP_CD")val REFINE_ZIP_CD:String?, //소재지우편번호
    @SerializedName("REFINE_LOTNO_ADDR")val REFINE_LOTNO_ADDR:String?, //소재지지번주소
    @SerializedName("REFINE_WGS84_LOGT")val REFINE_WGS84_LOGT:String?, //WGS84경도
    @SerializedName("REFINE_WGS84_LAT")val REFINE_WGS84_LAT:String? //WGS84위도

){

    override fun toString(): String {
        return "어린이집명 : " + KIDGARTN_NM +
                "\n" +
                " 시도명 : " + SIDO_NM +
                "\n" +
                " 시군구명 : " + SIGNGU_NM +
                "\n" +
                " 어린이집유형구분 : " + KIDGARTN_DIV_NM +
                "\n" +
                " 정원수 : " + PSN_CAPA_CNT +
                "\n" +
                " 보육교직원수 : " + CHLDCARE_SCHLSTAF_CNT +
                "\n" +
                " 소재지도로명주소 : " + REFINE_ROADNM_ADDR +
                "\n" +
                " 어린이집전화번호 : " + KIDGARTN_TELNO +
                "\n" +
                " 어린이집팩스번호 : " + KIDGARTN_FAXNO +
                "\n" +
                " 보육실수 : " + CHLDCR_ROOM_CNT +
                "\n" +
                " 놀이터수 : " + PLAYGRND_CNT +
                "\n" +
                " CCTV설치수 : " + CCTV_INSTL_CNT +
                "\n" +
                " 통학차량운영여부 : " + ATNDSKL_VEHCLE_OPERT_YN +
                "\n" +
                " 홈페이지주소 : " + HMPG_ADDR +
                "\n" +
                " 데이터기준일자 : " + DATA_STD_DE +
                "\n" +
                " 시군명 : " + SIGUN_NM +
                "\n" +
                " 시군코드 : " + SIGUN_CD +
                "\n" +
                " 소재지우편번호 : " + REFINE_ZIP_CD +
                "\n" +
                " 소재지지번주소 : " + REFINE_LOTNO_ADDR
    }
}