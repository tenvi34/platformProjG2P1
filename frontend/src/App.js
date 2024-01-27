import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import UserSignInPage from './user/signIn/UserSignInPage';
import UserSignUpPage from './user/signUp/UserSignUpPage';
import MusicSearchPage from './music/search/MusicSearchPage';
import MusicInfoPage from './music/info/MusicInfoPage';
import MusicManagePage from './music/manage/MusicManagePage';
import PlayListListPage from './playList/list/PlayListListPage';
import PlayListInfoPage from './playList/info/PlayListInfoPage';

function App() {
  return (
    <Router>
      <Routes>
            <Route path="/" element={<UserSignInPage/>}/>

            <Route path="/user/signIn" element={<UserSignInPage/>}/>
            <Route path="/user/signUp" element={<UserSignUpPage/>}/>

            <Route path="/music/search" element={<MusicSearchPage/>}/>
            <Route path="/music/info" element={<MusicInfoPage/>}/>
            <Route path="/music/manage" element={<MusicManagePage/>}/>

            <Route path="/playList/list" element={<PlayListListPage/>}/>
            <Route path="/playList/info" element={<PlayListInfoPage/>}/>
        </Routes>
    </Router>
  )
}

export default App;