/**
 * @Author: Wali Morris<walimmorris@gmail.com> 04-04-2023
 *
 * A simple javascript click stream process to track user click events on valid clickable elements.
 * Clickable elements in HTML files should contain a 'data-clickable-name' data attribute which lets
 * the tracker know if:
 *
 *     1. The element can be tracked
 *     2. The event name of the clickable element
 *
 * This is a simple tracker and is built for extension and additions to trackable properties. Currently
 * tracked properties are:
 *
 *     1. userId: userName
 *     2. deviceType: device being used
 *     3. clickEvent: click event which triggered stream creation
 *     4. clientTimestamp: time the click event occurred
 *
 * Example click stream json object that'll be posted to kinesis stream:
 *
 *     {
 *         "userId": "hitman",
 *         "deviceType": "desktop",
 *         "clickEvent": "author_linkedin_link_navigation",
 *         "clientTimestamp": "2023-05-04T21:03:02"
 *     }
 *
 * Click events are then passed to an API endpoint and processed into an Amazon Kinesis stream for further
 * storage and analytics purposes. Personal user data will never be tracked or stored.
 */

$(document).ready(function () {
    $(window).on('click', function (e) {
        track(e);
    });
});

function track(e) {
    const clickDataName = getClickableName(e);
    if (clickDataName !== null) {
        // build stream record
        const jsonStreamRecord = createStreamRecord(clickDataName);
        console.log(jsonStreamRecord);
        postClickStreamRecord(jsonStreamRecord);
    }
}

/**
 * Creates and preps a stream record in json format to pass to post analytics
 * pipeline via api endpoint.
 *
 * @param dataEventName the clickable event name
 * @return {{}} json
 */
function createStreamRecord(dataEventName) {
    let data = {};
    data['userId'] = getUserName();
    data['deviceType'] = getDeviceType();
    data['clickEvent'] = dataEventName;
    data['clientTimestamp'] = currentTimeStamp();
    return data;
}

function getUserName() {
    // currently in test
    return "hitman";
}

/**
 * Get Current Time Stamp in format year-month-day{T}hours-minutes-seconds.
 *
 * @return {string}
 */
function currentTimeStamp() {
    const today = new Date();
    const year = today.getFullYear();
    const month = `${today.getMonth() + 1}`.padStart(2, '0');
    const day = `${today.getDate()}`.padStart(2, '0');
    const date = [year, month, day].join('-');

    const hour = `${today.getHours()}`.padStart(2, '0');
    const minute = `${today.getMinutes()}`.padStart(2, '0');
    const second = `${today.getSeconds()}`.padStart(2, '0');
    const time = [hour, minute, second].join(':');
    return `${date}T${time}`;
}

/**
 * Get name for the clickable data element.
 *
 * @param e window element
 * @return {null|string}
 */
function getClickableName(e) {
    const element = e.target;
    if ($(element).data('clickable-name') !== undefined) {
        return element.dataset.clickableName;
    }
    return null;
}

/**
 * Get current device type. UserAgent is a property of the navigator object that indicates
 * the user agent which the browser provides in HTTP headers. Using the userAgent property
 * collect the device type and test against regex of different devices.
 *
 * @return {string}
 */
function getDeviceType() {
    const ua = navigator.userAgent;
    if (/(tablet|ipad|playbook|silk)|(android(?!.*mobi))/i.test(ua)) {
        return "tablet";
    }
    if (/Mobile|iP(hone|od)|Android|BlackBerry|IEMobile|Kindle|Silk-Accelerated|(hpw|web)OS|Opera M(obi|ini)/.test(ua)) {
        return "mobile";
    }
    return "desktop";
}

/**
 * Utilizes internal spring API to post click stream record with event properties.
 * The following are the expected event properties:
 *
 * {
 *     userId: String,
 *     deviceType: String,
 *     clickEvent: String,
 *     clientTimeStamp
 * }
 *
 * @param jsonRecord
 */
function postClickStreamRecord(jsonRecord) {
    $.ajax({
        async: false,
        contentType: "application/json; charset=UTF-8",
        url : "/clickstream/singlerecord",
        type: "POST",
        data : JSON.stringify(jsonRecord),
        success: function(data, textStatus) {
            console.log(data);
            console.log(textStatus)
        },
        error: function (textStatus, errorThrown) {
            console.log("error: ");
            console.log(textStatus);
        }
    });
}
