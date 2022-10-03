import store from '../store'
import VueCookies from 'vue-cookies'
import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/home/HomeView.vue'
import SearchView from '@/views/home/SearchView.vue'

import DiaryCreateView from '../views/diary/DiaryCreateView.vue'
import DiaryDetailView from '../views/diary/DiaryDetailView.vue'
import DiaryEditView from '../views/diary/DiaryEditView.vue'
// import DiaryCommentView from '../components/diary/DiaryCommentView.vue'

import LoginView from '@/views/account/LoginView.vue'
import SignUpView from '@/views/account/SignUpView.vue'
import SignUpOptionView from '@/views/account/SignUpOptionView.vue'
import SettingView from '@/views/account/SettingView.vue'
import PasswordFindView from '@/views/account/PasswordFindView.vue'
import PasswordChangeView from '@/views/account/PasswordChangeView.vue'

import ProfileView from '@/views/profile/ProfileView.vue'
import ProfileEditView from '@/views/profile/ProfileEditView.vue'

import CommunityCreateView from '@/views/community/CommunityCreateView.vue'
import CommunityView from '@/views/community/CommunityView.vue'
import CommunityDetailView from '@/views/community/CommunityDetailView.vue'
import CommunityEditView from '@/views/community/CommunityEditView.vue'
import ChatListView from '@/views/community/ChatListView.vue'

import BadgeListView from '../views/badge/BadgeListView.vue'

import NotFound404View from '@/views/handler/NotFound404View.vue'

const routes = [
  // 모두가 접근 가능한 route
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/search',
    name: 'search',
    component: SearchView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/signup',
    name: 'signUp',
    component: SignUpView
  },
  {
    path: '/signup/option',
    name: 'signUpOption',
    component: SignUpOptionView
  },
  {
    path: '/setting',
    name: 'setting',
    component: SettingView
  },
  {
    path: '/passwordchange',
    name: 'passwordChange',
    component: PasswordChangeView
  },
  {
    path: '/passwordfind',
    name: 'passwordFind',
    component: PasswordFindView
  },
  {
    path: '/404',
    name: 'notFound404',
    component: NotFound404View
  },
  {
    path: '/:catchAll(.*)',
    redirect: '/404'
  }, 


  // login한 사용자만 접근 가능한 route
  {
    path: '/diary/create',
    name: 'diaryCreate',
    component: DiaryCreateView
  },
  {
    path: '/diary/:diaryPk',
    // 나중에 pk 추가하기
    name: 'diaryDetail',
    component: DiaryDetailView
  },
  {
    path: '/diary/edit/:diaryPk',
    // 나중에 pk 추가하기
    name: 'diaryEdit',
    component: DiaryEditView
  },
  // {
  //   path: '/diary/comment',
  //   // 나중에 pk 추가하기
  //   name: 'diaryComment',
  //   component: DiaryCommentView
  // },


  {
    path: '/profile',
    // 나중에 pk 추가하기
    name: 'profile',
    component: ProfileView
  },
  {
    path: '/profile/edit',
    // 나중에 pk 추가하기
    name: 'profileEdit',
    component: ProfileEditView
  },


  {
    path: '/community',
    name: 'community',
    component: CommunityView
  },

  {
    path: '/community/:postPk',
        // 나중에 pk 추가하기
    name: 'communityDetail',
    component: CommunityDetailView
  },
  {
    path: '/community/create',
    name: 'communityCreate',
    component: CommunityCreateView
  },
  {
    path: '/community/edit/:postPk',
    name: 'communityEdit',
    component: CommunityEditView
  },

  
  {
    path: '/badge',
    name: 'badgeList',
    component: BadgeListView
  },
  
  {
    path: '/chat',
    name: 'chatList',
    component: ChatListView
  },
  

  
]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


router.beforeEach((to, from, next) => {
  // const { isLoggedIn } = store.getters
  const refreshToken = VueCookies.get('refreshToken')
  const accessToken = VueCookies.get('accessToken')

  const authPages = [
    'diaryCreate', 'diaryEdit', 'diaryDetail', 'diaryComment',
    'profile', 'profileEdit', 
    'community', 'communityEdit', 'communityDetail', 'communityCreate',
    'badgeList'
  ]

  const isAuthRequired = authPages.includes(to.name)
  
    if (isAuthRequired && !accessToken && refreshToken){
      //refreshToken은 있고 accessToken이 없을 경우 토큰 재발급 요청
      store.dispatch('reissueToken');
    }
    if (isAuthRequired && accessToken){
      //accessToken이 있을 경우 진행
      return next();
    }
    if (isAuthRequired && !accessToken && !refreshToken){
      console.log(store)
      //2개 토큰이 모두 없을 경우 로그인페이지로
      alert('로그인을 해주세요!')
      return next({name: 'login'});
    }
    return next();
})

export default router