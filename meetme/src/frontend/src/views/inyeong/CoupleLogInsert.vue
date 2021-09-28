<template>
    <div>
        <div class="container" style="background-color:white" >
            <div id="pageTitle" >
                <h3 class="text-center" style=" color:rgb(105, 198, 187)">커플로그 입력하기</h3>
            </div>
            <br>
            <div class="form-group row">
                <div class="col-lg-2 ">
                </div>
                <div class="col-lg-3">
                    <label for="logTitle">: </label>
                    <input type="text" placeholder="제목을 입력해주세요" id="logTitle">
                    <br>
                    <label for="logDate">날짜: </label>
                    <input type="date" placeholder="날짜를 입력해주세요" id="logDate">
                </div>
                <div class="col-lg-1">
                </div>
                <div class="col-lg-3">
                    <label for="logTitle">위치: </label>
                    <input type="text" placeholder="제목을 입력해주세요" id="logTitle">
                    <br>
                    <label for="logDate">: </label>
                    <input type="date" placeholder="날짜를 입력해주세요" id="logDate">
                </div>
                 <div class="col-lg-2">
                </div>
            </div>
            <br> 
            <hr>
            <br>
            <!--사진 여러 개 올리기-->
            <div class="mb-3">
               <UploadImages />
            </div>
            <br>
            <div class="text-center">
                 <button type="button" class="btn" onclick="location.href='/coupleLogList'"
                    style="background-color: rgb(105, 198, 187); color:white">
                    <i class="far fa-heart"></i>저장하기
                </button>
            </div> 
            <br>
            <!--미리보기 사진 하나하나에 캡션 달 수 있게 만들기-->
        </div>
    </div>
</template>
<script>
import UploadImages from "vue-upload-drop-images"

export default {
    name:'',
    components: {UploadImages},
    // data(){
    //     return{
    //         sampleData: '' 
    //     };
    // },

    methods: {}
}
</script>