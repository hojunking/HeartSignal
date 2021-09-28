<template>
    <div class="tm-page-wrap mx-auto">
        <div class="tm-container-outer" id="tm-section-2">
            <div class="row">
                <div class="col-lg-7">
                    <div class="row">
                        <div class="col-12">
                            <div class="form">
                                <form>
                                    <input class="form-control mt-3 mb-2 mx-5" type="text" placeholder=" # 태그를 선택해주세요">
                                </form>
                            </div>
                        </div>
                        <div class="col-12">
                            <Tags />
                        </div>
                    </div>
                </div>
                <div class="col-lg-5">
                    
                </div>
            </div>
		</div>
    </div>
</template>

<script>
import Tags from '../../components/courseComp/TagCreate.vue'

export default {
  name: 'CourseCreate',
  components: {
    Tags,
  }
}

</script>

<style>
    .insertcustom {
        border: 2px;
        border-style: solid;
        border-color: #69c6ba;
        border-radius:8px;
    }
</style>