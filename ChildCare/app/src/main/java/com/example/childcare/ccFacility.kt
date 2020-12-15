package com.example.childcare

data class ccFacility (
    val facName:String?, //어린이집명
    val facTypeName:String?, //어린이집유형구분
    val facStatus:String?, //어린이집유형구분
    val facAddr:String?, //상세주소
    val facTelno:String?, //어린이집전화번호
    val cctvCnt:Int?, //cctv설치수
    val teacherCnt:Int?, //보육직원수
    val maxChildCnt:Int?, //정원수
    val nowChildCnt:Int?, //현원수
    val ccBus:String?, //통학차량운영여부
    val la:String?, //위도
    val lo:String? //경도

)