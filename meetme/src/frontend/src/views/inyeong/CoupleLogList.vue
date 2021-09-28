<template>
    <div>
        <div class="container" style="background-color:white">
            <div id="pageTitle" style=" color:rgb(105, 198, 187)">
                <h3 class="text-center">커플로그</h3>
            </div>

            <main role="main" class="probootstrap-main js-probootstrap-main">
                <div>
                    
                </div>


                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                ...
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>

            </main>

            <!--aside 템플릿 사용-->
            <main role="main" class="probootstrap-main js-probootstrap-main">
                <div v-for="(item,index) in items" :key="index">
                    <div class="card-columns">
                        <a href="/CoupleLogDetail.vue">
                            <img class="card-img-top probootstrap-animate fadeInUp probootstrap-animated"
                                src="{{item.logImg}}" alt="사진">
                        </a>
                    </div>
                </div>
            </main>

            <!--button-->
            <div>

                <button type="button" class="btn" onclick="location.href='/coupleLogInsert'"
                    style="background-color: rgb(105, 198, 187); color:white">
                    <i class="far fa-heart"></i>새로운 추억 기록하기
                </button>
            </div>
        </div>

    </div>
</template>

<style scoped>
    #pageTitle {
        color: rgb(105, 198, 187),

    }
</style>
<script>
    export default [

    ]
</script>