import { createStore } from 'vuex';

import account from '@/store/modules/account.js'
import company from '@/store/modules/company.js'
import diary from '@/store/modules/diary.js'

export default createStore({
  modules: {
    account,
    company,
    diary,
  },
});