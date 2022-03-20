const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  URL: SERVER_URL,
  ROUTES: {
    // accounts
    signup: '/client-api/user/',
    co_signup: '/counselor-api/user/',
    login: '/client-api/login/',
    co_login: '/counselor-api/login/',


    // counseling
    counseling: '/schedule-api/',
    memoCreation: '/memo-api/write/',
    memoSelection: '/memo-api/',
    memosSelection: '/memo-api/list/',
    reviewCreation: '/review-api/save/',

    // home
    todayListeners: '/counselor-api/today/',

    // bookmark
    bookmarkCreation: `/bookmark/setlike/`,
    bookmarkSelection: `/bookmark/likecounselor/`,

    // profile
    counselingList: '/schedule-api/client/approved?page=',
    doneList: '/schedule-api/client/ended?page=',
    counselingListCounselor: '/schedule-api/counselor/approved?page=',
    doneListCounselor: '/schedule-api/counselor/ended?page=',
    // memoSelectionFromList: '/memo-api/counseling/',

    // reviews
    reviewsSelection: '/review-api/list/',

    // statistics
    getGender: '/statistics-api/gender/',
    getAge: '/statistics-api/age/',
    getMonth: '/statistics-api/month/',
  }
}