<template>
    <div class="tm-page-wrap mx-auto mt-3">
        <div class="tm-container-outer mt-5 p-5" id="tm-section-2">
            <div class="row">
                <div class="col-lg-7">
                    <div class="row">
                        <div class="col-10">
                            <div class="form-control">
                                <TagInput :options="options" :allowCustom="true" tagBgColor="#69c6ba" :customDelimiter="customDelimiter" v-model="tags" />
                            </div>
                        </div>
                        <div class="col-2 my-auto">
                            <button class="btn btn-primary mt-1" @click="searchByTag()">검색!</button>
                        </div>
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
                </div>
                <div class="col-lg-5">
                    <div class="flex m-10">
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

        onMounted(() => {
            fetchData();
        });
        
        
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
            loadingSearch.value = true;
            // I prefer to use fetch
            // you can use use axios as an alternative
            console.log(1);
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

            // course list
            enabled,
            list,
            dragging,

            // search
            searchData,
            loadingSearch,
            searchError,
            // search func
            searchByTag,
        };
    },
  
    components: {
        TagInput,
        draggable : VueDraggableNext
    },
    methods: {
      log(event) {
        console.log(event)
      },
    },
})

</script>

<style>
    .insertcustom {
        border: 2px;
        border-style: solid;
        border-color: #69c6ba;
        border-radius:8px;
    }
</style>