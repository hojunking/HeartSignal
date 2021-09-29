<template>
    <div class="tm-page-wrap mx-auto mt-3">
        <div class="tm-container-outer mt-5 p-5" id="tm-section-2">
            <div class="row">
                <div class="col-lg-7">
                    <div class="row">
                        <!-- 검색 창 -->
                        <div class="col-10">
                            <div class="form-control" @click="searched = false">
                                <TagInput :options="options" :allowCustom="true" tagBgColor="#69c6ba" :customDelimiter="customDelimiter" v-model="tags" />
                            </div>
                        </div>
                        <div class="col-2 my-auto">
                            <button class="btn btn-primary btn-lg mt-1" @click="searchByTag()">검색</button>
                        </div>

                        <!-- 검색 전 -->
                        <div v-if="!searched">
                            <div class="col-12">
                                <p v-if="loading">
                                    Still loading..
                                </p>
                                <p v-if="error">
                                </p>
                                <div v-if="!loading">
                                    <span v-for="post of data" :key="post.id">
                                        <button class="btn btn-primary m-1" @click="pushTag(post.tagId)">#{{ post.tagId }}</button>
                                    </span>
                                </div>
                            </div>
                        </div>

                        <!-- 검색 후 -->
                        <div v-if="searched">
                            <div class="col-12">
                                <p v-if="loadingSearch">
                                    Still loading..
                                </p>
                                <p v-if="searchError">
                                </p>
                                <div v-if="!loadingSearch" style="overflow:auto; height:40rem;">
                                    <div v-for="result of searchData" :key="result.id" class="m-2">
                                        <div class="p-4">
                                            <div class="row">
                                                <!-- 이미지 들어갈곳 -->
                                                <div class="col-2">
                                                    <!-- img api 검색 -->
                                                </div>
                                                <!-- 장소 내용 -->
                                                <div class="col-10">
                                                    <h4>
                                                        {{ result.placeName }}
                                                        <!-- 장소 자세히 보기 -->
                                                        <button class="btn btn-primary btn-sm mx-auto" 
                                                        @click="detailOfPlace(result.placeName)" data-bs-toggle="modal" data-bs-target="#placeModal">
                                                            자세히
                                                        </button>&nbsp;
                                                        <!-- 장소 코스에 추가하기 -->
                                                        <button class="btn btn-primary btn-sm mx-auto" 
                                                        @click="insertCourse(result.placeId)">
                                                            추가하기
                                                        </button>
                                                    </h4>
                                                    <p v-if="result.address">주소 : {{ result.address }}</p>
                                                    <p v-if="result.placePhone">전화번호 : {{ result.placePhone }}</p>
                                                    <p v-if="result.description">설명 : {{ result.description }}</p>
                                                </div>
                                            </div>
                                        </div>
                                       
                                        <hr class="my-2">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 검색 후 끝 -->
                    </div>
                </div>
                <!-- 코스 만들기 -->
                <div class="col-lg-5">
                    <div class="m-10">
                        <h1>나만의 코스</h1>
                        <draggable class="dragArea list-group w-full" :list="list" @change="log">
                            <div
                                class="list-group-item bg-gray-300 m-1 p-3 rounded-md text-center"
                                v-for="element in list"
                                :key="element.name"
                            >
                                {{ element.name }}
                            </div>
                        </draggable>
                    </div>
                </div>
                <!-- 코스 만들기 끝 -->
            </div>
		</div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="placeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">{{ modalTitle }}</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ...
                <!-- 
                    검색 api를 통해서 보여주는 곳 
                    1. 사진
                    2. 내용
                -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, onMounted, defineComponent } from 'vue'
import TagInput from '@mayank1513/vue-tag-input'
import { VueDraggableNext } from 'vue-draggable-next'
/**
 * todo: 라인 만들고, 버튼 누르면 검색 결과 나오고 그 선택한 디비전을 추가해서 옆에 추가하는것.
 */
export default defineComponent ({
    name: 'CourseCreate',
    setup() {
        
        /**
         *  태그 이용 검색
         */
        // 태그 가져오기
        const data = ref(null);
        const loading = ref(true);
        const error = ref(null);

        // 태그 등록
        const tags = ref([])
        const customDelimiter = [',', ' ']
        const options = ref([])
        
        // 태그 가져오는 fetch
        function fetchData() {
            loading.value = true;
            // I prefer to use fetch
            // you can use use axios as an alternative
            return fetch('api/course/place/tags', {
                method: 'get',
                headers: {
                'content-type': 'application/json'
                }
            })
            .then((res) => {
                // a non-200 response code
                if (!res.ok) {
                    // create error instance with HTTP status text
                    const error = new Error(res.statusText);
                    error.json = res.json();
                    throw error;
                }
                return res.json()
            })
            .then(json => {
                // set the response data
                console.log(json)
                data.value = json;
                for(let i=0; i<json.length; i++) {
                    options.value.push(json[i].tagId);
                }
            })
            .catch(err => {
                error.value = err;
                // In case a custom JSON error response was provided
                if (err.json) {
                    return err.json.then(json => {
                        // set the JSON response message
                        error.value.message = json.message;
                    });
                }
            })
            .then(() => {
                loading.value = false;
            });
        }

        // 클릭한 태그 가져오기
        const pushTag = (tagId) => {
            tags.value.push(tagId);
        };

        // 페이지 진입하자마자 실행.
        onMounted(() => {
            fetchData();
        });
        

        /**
         *  세부적인 장소 보여주기
         */
        // 모달 추가
        const modalTitle = ref(null)
        const detailOfPlace = (placeName) => {
            console.log(placeName)
            // 코스 디테일 모달 추가.
            modalTitle.value = placeName
        }


        /**
         * 코스 추가하기.
         */
        const insertCourse = (placeId) => {
            console.log(placeId)
            // 코스를 추가 해주는것.
        }


        /**
         *  코스 순서 정해주기
         */
        const enabled = ref(true)
        const list = ref([
          { name: 'John', id: 1 },
          { name: 'Joao', id: 2 },
          { name: 'Jean', id: 3 },
          { name: 'Gerard', id: 4 },
        ])
        const dragging = ref(true)


        /**
         * 검색하기
         */
        const searchData = ref(null);
        const loadingSearch = ref(false);
        const searchError = ref(null);
        const searched = ref(null);       

        // 검색 버튼
        const searchByTag = (evt) => {
            if(evt) {
                evt.preventDefault()
            }
            let keywords = "";
            for(let i=0; i<tags.value.length; i++) {
                keywords += tags.value[i],
                keywords += ',';
            }
            if(keywords.substring(keywords.length-1) === ',') {
                keywords = keywords.substring(0, keywords.length-1);
            }
            console.log(keywords)

            // search start
            searched.value = true;
            loadingSearch.value = true;
            // I prefer to use fetch
            // you can use use axios as an alternative
            console.log(1);
            if(keywords == '') {
                alert('검색어를 입력해주세요!')
                return
            }
            fetch('api/course/place/search?keywords=' + keywords, {
                method: 'get',
                headers: {
                'content-type': 'application/json'
                }
            })
            .then((res) => {
                // a non-200 response code
                if (!res.ok) {
                    // create error instance with HTTP status text
                    const error = new Error(res.statusText);
                    error.json = res.json();
                    throw error;
                }
                return res.json()
            })
            .then(json => {
                // set the response data
                console.log(json)
                searchData.value = json;
            })
            .catch(err => {
                searchError.value = err;
                // In case a custom JSON error response was provided
                if (err.json) {
                    return err.json.then(json => {
                        // set the JSON response message
                        searchError.value.message = json.message;
                    });
                }
            })
            .then(() => {
                loadingSearch.value = false;
            });
        }

        return {
            // tags
            data,
            loading,
            error,

            tags,
            customDelimiter,
            options,

            // tag function
            pushTag,

            // placeDetail
            detailOfPlace,
            modalTitle,
            
            // course insert
            insertCourse,

            // course list move
            enabled,
            list,
            dragging,

            // search
            searched,
            searchData,
            loadingSearch,
            searchError,
            // search func
            searchByTag,
        };
    },
  
    components: {
        TagInput,
        draggable : VueDraggableNext,
    },
    methods: {
      log(event) {
        console.log(event)
      },
    },
})

</script>

<style>
  
</style>