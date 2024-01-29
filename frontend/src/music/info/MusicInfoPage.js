import React from 'react';
import { useParams } from 'react-router-dom';

const MusicInfoPage = () => {
    const {musicId} = useParams();

    console.log(musicId)

    return (
        <>
            MusicInfoPage
        </>
    )
}

export default MusicInfoPage;