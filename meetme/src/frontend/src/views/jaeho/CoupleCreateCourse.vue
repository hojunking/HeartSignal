<template>
    <div class="tm-page-wrap mx-auto">
        <div class="tm-container-outer p-5" id="tm-section-2">
            <div class="row">
                <div class="col-12 px-4 d-flex justify-content-between">
                    <h1 class="display-4">코스 만들기</h1>
					
                </div>
                <div class="col-lg-7">
                    <div class="row">
                        <!-- 검색 창 -->
                        <div class="col-12">
                            <hr class="m-2">
                        </div>
                        <div class="col-10 px-4">
                            <div class="form-control" @click="searched = false">
                                <TagInput :options="options" :allowCustom="true" tagBgColor="#f73e69" :customDelimiter="customDelimiter" v-model="tags" />
                            </div>
                        </div>
                        <div class="col-2 my-auto">
                            <button class="btn btn-primary btn-lg mt-1" @click="searchByTag" type="button">검색</button>
                        </div>
                        <div class="col-12">
                            <hr class="m-2">
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
                                                <div class="col-3">
                                                    <img :src="result.thumbnailHref" class="img-thumbnail" alt="result.placeName">
                                                </div>
                                                <!-- 장소 내용 -->
                                                <div class="col-9">
                                                    <h4>
                                                        {{ result.placeName }}
                                                        <!-- 장소 자세히 보기 -->
                                                        <button class="btn btn-light btn-sm mx-2 " 
                                                        @click="detailOfPlace(result.placeName)" data-bs-toggle="modal" data-bs-target="#placeModal">
                                                            자세히
                                                        </button>&nbsp;
                                                        <!-- 장소 코스에 추가하기 -->
                                                        <button class="btn btn-primary btn-sm" 
                                                        @click="sendInsertCourse(result.placeName)">
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
                        <hr class="m-2">

                    </div>
                </div>
                <!-- 코스 만들기 -->
                <div class="col-lg-5">
                    <div class="m-10">
                        <!-- 디비에 저장된다. -->
                        <div class="d-flex justify-content-between">
                            <span class="fs-4">나만의 코스</span>
                            <button class="btn btn-primary m-2 my-auto"
                             data-bs-toggle="modal" data-bs-target="#courseTitleModal"   
                            >코스 등록
                            </button>
                        </div>
                        <draggable class="dragArea list-group w-full" :list="list" @change="changeChack">
                            <div class="list-group-item bg-gray-300 m-1 p-3 rounded-md"
                                v-for="element in list"
                                :key="element.name">
                                <div class="d-flex justify-content-between">
                                    <span class="fs-5">{{ element.placeName }}</span>
                                    <button type="button" class="btn-close btn-lg m-2" @click="deletePlaceInCourse(element.placeId)"
                                    ></button>
                                </div>
                                <div>
                                    <span class="text-muted mx-2">{{element.subTitle}}</span>
                                    <a type="button" class="fas fa-pen text-black fs-6 m-2" @click="replaceSubtitle(element.placeId ,element.subTitle)" style="text-decoration: none;"
                                    data-bs-toggle="modal" data-bs-target="#replaceSubtitleModal"></a>
                                </div>
                                <img :src="element.thumbnailHref" class="img-thumbnail" :alt="element.placeName">
                            </div>
                        </draggable>
                    </div>
                </div>
                <!-- 코스 만들기 끝 -->
            </div>
		</div>
    </div>
    

    <!-- Modal place Detail-->
    <div class="modal fade" id="placeModal" tabindex="-1" aria-labelledby="modalPlaceDetailLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalPlaceDetailLabel">{{ modalTitle }}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div v-if="!(modalLoading && modalImagesLoading) == true">
                        <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img :src="modalData.thumbnailHref" class="d-block w-100" style="width: 32rem; height: 18rem;" alt="..." v-if="modalData != null">
                                </div>
                                <div class="carousel-item" v-for="image of modalImagesData" :key="image.id">
                                    <img :src="image.thumbnail" style="width: 32rem; height: 18rem;" class="d-block w-100" alt="...">
                                </div>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                        <div v-if="modalData != null">
                            <span v-if="modalData.placeName">장소 : {{modalData.placeName}}</span><br>
                            <span v-if="modalData.address">주소 : {{modalData.address}}</span><br>
                            <span v-if="modalData.avgCost">평균 비용 : {{modalData.avgCost}}</span><br>
                            <span v-if="modalData.description">설명 : {{modalData.description}}</span><br>
                            <span v-if="modalData.placePhone">전화번호 : {{modalData.placePhone}}</span><br>
                        </div>
                        <!-- 지도 열어보자 -->
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>
    
    <!-- replaceSubtitle Modal -->
    <div class="modal fade" id="replaceSubtitleModal" tabindex="-1" aria-labelledby="replaceSubtitleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="replaceSubtitleModalLabel">소제목 수정하기</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="replaceFormInput" class="form-label">원하시는 소제목을 적어주세요!</label>
                        <input type="email" class="form-control" id="replaceFormInput" placeholder="저녁 코스 ㅎㅎ" v-model="replaceSubtitleText">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal"
                    @click="changeSubTitle(replaceSubtitleOfPlaceId, replaceSubtitleText)">저장 후 닫기</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Course title register Modal -->
    <div class="modal fade" id="courseTitleModal" tabindex="-1" aria-labelledby="courseTitleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="courseTitleModalLabel">코스 이름 정하기</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="titleFormInput" class="form-label">마지막으로 코스 이름을 적어주세요!</label>
                        <input type="email" class="form-control" id="titleFormInput" placeholder="멋진 데이트 코스!" v-model="titleText">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal"
                    @click="registerCourse(titleText)">등록</button>
                </div>
            </div>
        </div>
    </div>
    <div class="fixed-bottom text-end p-5">   
        <div class="btn-group dropup">
            <button type="button" class="far fa-comments btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                채팅
            </button>
            <ul class="dropdown-menu fs-6" >
                <div id="chatDiv" style="overflow:auto; height:15rem;">
                    <div v-for="(item, idx) in recvList" :key="idx" >
                        <li><span class="dropdown-item-text">{{ item.userName }}: {{ item.content }}</span></li>
                    </div>
                </div>
                <li><hr class="dropdown-divider"></li>
                <li><input v-model="message" type="text" @keyup="sendMessage"></li>
            </ul>
        </div>
    </div>
    
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
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
         * 채팅 기능
         */

        // 채팅
        // const userName = ref("");
        const message = ref("");
        const recvList = ref([]);

        const sendMessage = (e) => {
            if(e.keyCode === 13 && message.value !== ''){
                send()
                message.value = ''
            }
            
        }
        const send = () => {
            console.log("Send message:" + message.value);
            if (stompClient && stompClient.connected) {
                const msg = {
                    userName: "default",
                    content: message.value 
                };
                console.log(JSON.stringify(msg))
                stompClient.send("/chatReceive", JSON.stringify(msg), {});
            }
        }

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
        // 태그 웹소캣 추가하기
        // const sendTags = (tagIdN) => {
        //     console.log("Send tags:" + tagIdN);
        //     if (stompClient && stompClient.connected) {
        //         const msg = { 
        //             tagId : tagIdN
        //         };
        //         console.log(JSON.stringify(msg))
        //         stompClient.send("/tagReceive", JSON.stringify(msg), {});
        //     }
        // }

        // 페이지 진입하자마자 실행.
        onMounted(() => {
            fetchData();
            // securityFetch();
        });
        

        /**
         *  세부적인 장소 보여주기
         */
        // 모달 추가
        const modalTitle = ref(null)
        const modalLoading = ref(false)
        const modalData = ref(null)

        const modalImagesLoading = ref(false)
        const modalImagesData = ref(null);
        
        const modalError = ref(null)

        const detailOfPlace = (placeName) => {
            console.log(placeName)
            // 코스 디테일 모달 추가.
            modalTitle.value = placeName
             // search start
            modalLoading.value = true;
            modalImagesLoading.value = true;
            console.log(2);
            if(placeName == '') {
                alert('json 호출 중 에러')
                return
            }
            // 장소 디테일 가져오기
            fetch('api/course/place/searchOne/' + placeName, {
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
                modalData.value = json;
            })
            .catch(err => {
                searchError.value = err;
                // In case a custom JSON error response was provided
                if (err.json) {
                    return err.json.then(json => {
                        // set the JSON response message
                        modalError.value.message = json.message;
                    });
                }
            })
            .then(() => {
                modalLoading.value = false;
            });


            //장소 이미지들 가져와서 Carosel로다가 만들기
            fetch('api/course/place/searchOneImages?placeName=' + placeName, {
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
                modalImagesData.value = json.items;
            })
            .catch(err => {
                searchError.value = err;
                // In case a custom JSON error response was provided
                if (err.json) {
                    return err.json.then(json => {
                        // set the JSON response message
                        modalError.value.message = json.message;
                    });
                }
            })
            .then(() => {
                modalImagesLoading.value = false;
            });
        }


        /**
         * 코스 추가하기.
         */
        const insertPlaceData = ref(null);
        const insertLoading = ref(false);
        const insertError = ref(null);

        const sendInsertCourse = (placeName) => {
            console.log("Send insertCourse:" + placeName);
            if (stompClient && stompClient.connected) {
                const msg = { 
                    insertCourse : placeName
                };
                console.log(JSON.stringify(msg))
                stompClient.send("/insertCourseReceive", JSON.stringify(msg), {});
            }
        }

        const insertCourse = (placeName) => {
            console.log(placeName)
            // 코스를 추가 해주는것.
            // 부제목 넣기
        
            // 장소 디테일 가져오기
            insertLoading.value = true;
            fetch('api/course/place/searchOne/' + placeName, {
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
                insertPlaceData.value = json;
            })
            .catch(err => {
                searchError.value = err;
                alert("코스입력 문제 발생")
                // In case a custom JSON error response was provided
                if (err.json) {
                    return err.json.then(json => {
                        // set the JSON response message
                        insertError.value.message = json.message;
                    });
                }
            })
            .then(() => {
                insertLoading.value = false;
                insertPlaceData.value.subTitle = "소제목";
                list.value.push(insertPlaceData.value)
            });
        }

        /**
         *  코스 순서 정해주기
         */
        const enabled = ref(true)
        const list = ref([])
        const dragging = ref(true)
        // 코스 삭제하기
        const deletePlaceInCourse = (placeIdX) => {
            console.log(placeIdX)
            console.log(list.value)
            for( let i = 0; i < list.value.length; i++){ 
                if ( list.value[i].placeId === placeIdX) { 
                    list.value.splice(i, 1); 
                }
            }
            sendChangeList();
        }
        
        // 코스 이동 체크
        const changeChack = (evt) => {
            console.log(evt)
            if(evt.moved) {
                console.log(evt.moved.element.placeId);
                console.log(list.value);
                sendChangeList();
            }
        }

        // 코스 수정 웹소캣 보내기
        const sendChangeList = () => {
            console.log("Send changedList:" + list.value);
            if (stompClient && stompClient.connected) {
                const msg = { 
                    changedList : list.value
                };
                console.log(JSON.stringify(msg))
                stompClient.send("/changeListReceive", JSON.stringify(msg), {});
            }
        }

        // 코스 소제목 수정
        const replaceSubtitleOfPlaceId = ref(null);
        const replaceSubtitleText = ref(null);
        const replaceSubtitle = (placeId, subTitle) => {
            console.log(subTitle)
            replaceSubtitleOfPlaceId.value = placeId;
            if(subTitle != '소제목') {
                replaceSubtitleText.value = subTitle
            }
        }

        const changeSubTitle = (placeId, subTitle) => {
            for( let i = 0; i < list.value.length; i++){ 
                if ( list.value[i].placeId === placeId ) { 
                    list.value[i].subTitle = subTitle; 
                }
            }
            replaceSubtitleOfPlaceId.value = null;
            replaceSubtitleText.value = null;
            sendChangeList();
        }


        /**
         * 검색하기
         */
        const searchData = ref(null);
        const loadingSearch = ref(false);
        const searchError = ref(null);
        const searched = ref(null);       

        // 검색 웹소캣 보내기
        // const sendSearchByTag = (evt) => {
        //     if(evt) {
        //         evt.preventDefault()
        //     }
        //     console.log("Send searchByTag:" + " 검색");
        //     if (stompClient && stompClient.connected) {
        //         const msg = { 
        //             searchByTag : "검색"
        //         };
        //         console.log(JSON.stringify(msg))
        //         stompClient.send("/searchByTagReceive", JSON.stringify(msg), {});
        //     }
        // }

        // 검색 버튼 활성화 -> connect 안으로 보냄
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


        /**
         *   코스 등록
         */
        // 코스 등록 버튼 
        const titleText = ref(null);
        let sendList = null;
        let subList = null;
        let sendTitleText = null;
        const registerCourseError = ref(null);
        const registerConfirm = ref(null);
        const registerCourse = (titleTextx) => {
            console.log(list.value);
            console.log(titleTextx);

            if(list.value.length == 0) {
                alert('장소를 등록해 주세요!')
                return;
            }
            
            if(titleText.value == null || titleText.value == "") {
                titleText.value = "기본값 코스"
            }

            sendTitleText = titleText.value
            console.log(sendTitleText);

            sendList = [];
            for( let i = 0; i < list.value.length; i++){ 
                subList = null;
                subList = {};
                //     PLACE_ID,
                // COURSE_ORDER,
                // COURSE_COMMENT,
                // AVG_COST,
                subList.placeId = list.value[i].placeId;
                subList.courseOrder = (i+1) + "";
                subList.courseComment = list.value[i].subTitle
                subList.avgCost = list.value[i].avgCost

                sendList.push(subList);
            }
            console.log(JSON.stringify(sendList));
            for(let i=0; i<sendList.length; i++) {
                console.log(sendList[i]);
            }
            // I prefer to use fetch
            // you can use use axios as an alternative
            var formData = new FormData();
            formData.append("courseName", sendTitleText);
            formData.append("list", JSON.stringify(sendList))
            fetch('api/course/register', {
                credentials: 'include',
                method: 'POST',
                body: formData
            })
            .then((res) => {
                // a non-200 response code
                if (!res.ok) {
                    // create error instance with HTTP status text
                    const error = new Error(res.statusText);
                    error.json = res.json();
                    throw error;
                }
                return res.text();
            })
            .then(text => {
                // set the response data
                console.log(text)
                registerConfirm.value = text;
            })
            .catch(err => {
                registerCourseError.value = err;
                // In case a custom JSON error response was provided
                if (err.json) {
                    return err.json.then(json => {
                        // set the JSON response message
                        registerCourseError.value.message = json.message;
                    });
                }
            })
            .then(() => {
                if ((registerConfirm.value) && (registerConfirm.value != "error")) {
                    alert('등록이 되었습니다.');
                    location.href = "/courseDetail?courseId=" + registerConfirm.value;
                }
                else if(registerConfirm.value == "error"){
                    alert('등록에 실패하였습니다.');
                }
                else {
                    alert('알 수 없는 문제가 발생 하였습니다.');
                }
            });
        }

        // 스프링 부트 웹소켓 연결
        let stompClient = null;
        const connect = () => {
            const serverURL = "http://localhost:8000/ws"
            let socket = new SockJS(serverURL);
            stompClient = Stomp.over(socket);
            console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
            stompClient.connect(
                {},
                frame => {
                // 소켓 연결 성공
                    stompClient.connected = true;
                    console.log('소켓 연결 성공', frame);
                    // 서버의 메시지 전송 endpoint를 구독합니다.
                    // 이런형태를 pub sub 구조라고 합니다.
                    stompClient.subscribe("/send", res => {
                        console.log('구독으로 받은 메시지 입니다.', res.body);
                        
                        // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                        // 채팅
                        if (JSON.parse(res.body).content) {
                            recvList.value.push(JSON.parse(res.body))
                            setTimeout(function(){
                                document.getElementById('chatDiv').scrollTop = document.getElementById('chatDiv').scrollHeight;
                            }, 1);
                        // // 태그 검색어 추가
                        // } else if (JSON.parse(res.body).tagId){
                        //     tags.value.push(JSON.parse(res.body).tagId);
                        // // 태그 검색
                        // } else if (JSON.parse(res.body).searchByTag) {
                        //     searchByTag();
                        // 코스 추가하기
                        } else if (JSON.parse(res.body).insertCourse) {
                            insertCourse(JSON.parse(res.body).insertCourse)
                        // 코스가 바뀔때마다 영향주기
                        } else if (JSON.parse(res.body).changedList) {
                            list.value = JSON.parse(res.body).changedList;
                        } else {
                            console.error(JSON.parse(res.body));
                        }
                    });
                },
                error => {
                    // 소켓 연결 실패
                    console.log('소켓 연결 실패', error);
                    stompClient.connected = false;
                }
            );        
        }

        connect();

        return {
            // chat
            message,
            recvList,
            sendMessage,

            // tags
            data,
            loading,
            error,

            tags,
            customDelimiter,
            options,

            // tag function
            pushTag,
            //sendTags,

            // placeDetail
            detailOfPlace,
            modalTitle,
            modalLoading,
            modalData,
            modalError,
            modalImagesLoading,
            modalImagesData,

            // course insert
            sendInsertCourse,
            insertCourse,
            insertPlaceData,
            insertLoading,
            insertError,

            // course list move
            enabled,
            list,
            dragging,
            deletePlaceInCourse,
            replaceSubtitle,
            replaceSubtitleText,
            replaceSubtitleOfPlaceId,
            changeSubTitle,

            // list move check
            changeChack,
            sendChangeList,

            // search
            searched,
            searchData,
            loadingSearch,
            searchError,
            // search func
            // sendSearchByTag,
            searchByTag,

            // last.. register course
            registerCourse,
            titleText,
            sendTitleText,
            sendList,
            subList,
            
            // 스프링 부트 웹소캣 연결
            connect
        };
    },
  
    components: {
        TagInput,
        draggable : VueDraggableNext,
    }
})

</script>

<style>
  
</style>