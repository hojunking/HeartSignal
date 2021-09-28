<template>
    <p v-if="loading">
        Still loading..
    </p>
    <p v-if="error">
    </p>
    <div v-if="!loading">
        <span v-for="post of data" :key="post.id">
            <button class="btn btn-primary m-1">#{{ post.tagId }}</button>
        </span>
    </div>
</template>

<script>

import { ref, onMounted } from 'vue'

export default {
    name: 'tags',
    props: {
    },
    setup() {
        const data = ref(null);
        const loading = ref(true);
        const error = ref(null);

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

        onMounted(() => {
            fetchData();
        });

        return {
            data,
            loading,
            error
        };
    }
}
</script>

<style>
</style>
