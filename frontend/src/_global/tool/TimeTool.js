class TimeTool {
    static SecondToAudioTime(sec) {
        return String(Math.floor(sec/60)).padStart(2, '0') + ":" + String(sec%60).padStart(2, '0')
    }
}

export default TimeTool;