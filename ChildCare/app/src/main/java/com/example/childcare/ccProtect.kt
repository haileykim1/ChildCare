package com.example.childcare

data class ccProtect (
    val facType:String?, //시설종류 : FCLTY_KND
    val facTargetName:String?, //대상시설명 : TRGET_FCLTY_NM
    val roadAddr:String?, //소재지도로명주소 : RDNMADR
    val nameAddr:String?, //소재지지번주소 : LNMADR
    val la:String?, //위도 : LATITUDE
    val lo:String?, //경도 : LONGITUTE
    val institution:String?, //관리기관명 : INSTITUTION_NM
    val policeStn:String?, //관할경찰서명 : CMPTNC_POLCSTTN_NM
    val cctvYN:String?, //CCTV설치여부 : CCTV_YN
    val cctvCnt:String?, //CCTV설치대수 : CCTV_NUMBER
    val carRoadWidth:String?, //보호구역도로폭 : PRTCAREA_RW
    val referenceDate:String?, //데이터기준일자
    val insttCode:String?//제공기관코드
){
    override fun toString(): String {
        return "시설종류 : " + facType +
                "\n" +
                " 대상시설명 : " + facTargetName +
                "\n" +
                " 소재지도로명주소 : " + roadAddr +
                "\n" +
                " 소재지지번주소 : " + nameAddr +
                "\n" +
                " 관리기관명 : " + institution +
                "\n" +
                " 관할경찰서명 : " + policeStn +
                "\n" +
                " CCTV설치여부 : " + cctvYN +
                "\n" +
                " CCTV설치대수 : " + cctvCnt +
                "\n" +
                " 보호구역도로폭 : " + carRoadWidth +
                "\n" +
                " 데이터기준일자 : " + referenceDate +
                "\n" +
                " 제공기관코드 : " + insttCode
    }
}