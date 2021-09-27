import { createStore } from 'vuex'

export const store = createStore({
    state: {
        access_token: sessionStorage.getItem("access_token"),
        refresh_token: sessionStorage.getItem("refresh_token"),
        expires_in: "",
        claims: JSON.parse(sessionStorage.getItem("claims")),
        intervalId: null
    },
    getters: {
        getTokenExpiresIn(state) {
          return (state.claims.exp - Math.floor(new Date().getTime() / 1000) < 0 ? "" : state.claims.exp - Math.floor(new Date().getTime() / 1000));
        },
        getIntervalId(state) {
          return state.intervalId;
        },
        getClaims(state) {
          return state.claims;
        }
    },
    mutations: {
        loginToken(state, data) {
          sessionStorage.setItem("access_token", data.access_token);
          sessionStorage.setItem("refresh_token", data.refresh_token);
    
          state.access_token = data.access_token;
          state.refresh_token = data.refresh_token;
          state.expires_in = data.expires_in;
        },
        delToken(state) {
          sessionStorage.removeItem('access_token');
          sessionStorage.removeItem('refresh_token');
          sessionStorage.removeItem('claims');
          if (state.access_token)
            state.access_token = null;
          if (state.refresh_token)
            state.expires_in = null;
          if (state.claims)
            state.claims = null;
        },
        setAllClaims(state, claims) {
          state.claims = claims;
        },
        setJwtExpiresIn(state, expires_in) {
          state.expires_in = expires_in;
        },
        setIntervalId(state, intervalId) {
          state.intervalId = intervalId;
        }
    },
    getAllClaimsFromToken(context, username) {
        fetch({
            method: "post",
            url: "/api/auth/claims", // 여기에는 로그인 특성이 담겨야 하므로 수정 해야함.
            data: {
              "username": username
            }
          })
          .then(result => {
            sessionStorage.setItem("claims", JSON.stringify(result.data));
            context.commit("setAllClaims", result.data);
            context.dispatch("setJwtExpiresIn");
          })
          .catch(error => {
            console.log(error);
          });
      },
      setJwtExpiresIn(context) {
        if (context.state.claims != null && context.state.claims.exp != undefined) {
          var intervalId = setInterval(() => {
            let expires_in = (context.state.claims.exp - Math.floor(new Date().getTime() / 1000) < 0 ? "" : context.state.claims.exp - Math.floor(new Date().getTime() / 1000));
            context.commit('setJwtExpiresIn', expires_in)
          }, 1000)
  
          context.commit("setIntervalId", intervalId);
        }
  
      },
      destroySetJwtExpiresInScheduler(context) {
        clearInterval(context.getters.getIntervalId);
      }
})