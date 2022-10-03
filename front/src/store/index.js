import { createStore } from 'vuex';

import account from '@/store/modules/account.js'
import community from '@/store/modules/community.js'
import diary from '@/store/modules/diary.js'
import home from '@/store/modules/home.js'
import profile from '@/store/modules/profile.js'

export default createStore({
  modules: {
    account,
    community,
    diary,
    home,
    profile
  }
});