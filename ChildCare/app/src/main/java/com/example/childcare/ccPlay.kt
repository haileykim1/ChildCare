package com.example.childcare

data class ccPlay (
    val playName:String?, //놀이시설명 : ciName
    val nAddr:String?, //지번주소 :
    val rAddr:String?, //도로명주소 :
    val locate:String?, //설치장소코드명
    val name4:String?, //민간공공구분코드명
    val name21:String? //실내외구분코드명

){
    override fun toString(): String {
        return "놀이시설명 : " + playName +
                "\n" +
                " 지번주소 : " + nAddr +
                "\n" +
                " 도로명주소 : " + rAddr +
                "\n" +
                " 설치장소코드명 : " + locate +
                "\n" +
                " 민간공공구분코드명 : " + name4 +
                "\n" +
                " 실내외구분코드명 : " + name21
    }
}