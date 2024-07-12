const BASE_URL = 'http://localhost:8080';

const POSTS_URL = '/posts';

const MAKE_POST = '/make-post';

const postSection = document.createElement('div');

function createPostSection() {
    postSection.setAttribute('id', `postSection`);
    postSection.setAttribute('style', 'z-index: 0');
    document.body.append(postSection);
    return postSection;
}

createPostSection();

const user = {
    id: 0,
    name: 'First',
    username: 'first',
    email: 'onetest@test',
    password: '123',
    isAuthorised: false
};

console.log(user);

const firstPost = {
    id: 1,
    imageName: '1666156904606.jpg',
    description: 'some desc',
    date: new Date().toLocaleDateString('ru-RU', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    }),
    time: new Date().toLocaleTimeString('ru-RU', {
        hour: '2-digit',
        minute: '2-digit'
    }),
    user: user,
    isLiked: false
};

const secondPost = {
    id: 2,
    imageName: 'somepic.jpg',
    description: 'some desc',
    date: new Date().toLocaleDateString('ru-RU', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    }),
    time: new Date().toLocaleTimeString('ru-RU', {
        hour: '2-digit',
        minute: '2-digit'
    }),
    user: user,
    isLiked: false
};

const thirdPost = {
    id: 3,
    imageName: 'somepic.jpg',
    description: 'some desc',
    date: new Date().toLocaleDateString('ru-RU', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    }),
    time: new Date().toLocaleTimeString('ru-RU', {
        hour: '2-digit',
        minute: '2-digit'
    }),
    user: user,
    isLiked: false
};

console.log(firstPost);

const comment = {
    id: 0,
    text: 'some text',
    date: new Date().toLocaleDateString('ru-RU', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    }),
    time: new Date().toLocaleTimeString('ru-RU', {
        hour: '2-digit',
        minute: '2-digit'
    }),
    post: firstPost,
    user: user
};

console.log(comment);

// const secondComment = {
//     id: 1,
//     text: 'some other text',
//     date: new Date().toLocaleDateString('ru-RU', {
//         day: '2-digit',
//         month: '2-digit',
//         year: 'numeric'
//     }),
//     time: new Date().toLocaleTimeString('ru-RU', {
//         hour: '2-digit',
//         minute: '2-digit'
//     }),
//     post: secondPost,
//     user: user
// }

const posts = [firstPost, secondPost, thirdPost];

function setIdsForPosts() {
    for (let i = 0; i < posts.length; i++) {
        posts[i].id = i + 1;
    }
}

console.log(posts);

const post = {
    id: 0,
    imagePath: 'somepic.jpg',
    description: 'some desc',
    date: new Date().toLocaleDateString('ru-RU', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    }),
    time: new Date().toLocaleTimeString('ru-RU', {
        hour: '2-digit',
        minute: '2-digit'
    }),
    user: user,
    isLiked: false
};

function authorizeUser(user) {
    user.isAuthorised = true;
}

authorizeUser(user);

console.log(user);

function likePost(posts, postId) {
    if (postId >= posts[0].id && postId <= posts.length) {
        let currentPost = posts[postId - 1];
        currentPost.isLiked = !currentPost.isLiked;
        return currentPost;
    }
}

likePost(posts, 4);

function hideSplashScreen() {
    const splashScreen = document.getElementById('bg');
    splashScreen.classList.toggle('d-none');
    // const showSplash = document.getElementById('showSplash');
    // showSplash.classList.remove('d-none');
    // showOrHidePostSection();
    // showOrHideCommentSection();
}

function showSplashScreen() {
    const splashScreen = document.getElementById('bg');
    splashScreen.classList.toggle('d-none');
    // showOrHidePostSection();
    // showOrHideCommentSection();
}

// function showOrHidePostSection() {
    // const post = document.getElementById(`thisPost${post.id}`);
// post.classList.toggle('d-none');
// }

// function showOrHideCommentSection() {
//     const comment = document.getElementById(`commentSection${post.id}`);
//     if (!comment.classList.contains('d-none')) {
//         comment.classList.add('d-none');
//     }
// }

// function createCommentSection(post) {
//     let commentSection = document.createElement('div');
//     post = document.getElementById(`postElement${post.id}`);
//     commentSection.setAttribute('id', `commentSection${comment.post.id}`);
//     post.append(commentSection);
//     return commentSection;
// }

// function toggleCommentSection(comment) {
//     const commentSection = document.getElementById(`commentSection${comment.post.id}`);
//     commentSection.classList.toggle('d-none');
// }

function createCommentElement(comment, post) {
    let postElement = document.getElementById(`postElement${post.id}`);
    let commentSection = createCommentSection(post);
    let commentElement = document.createElement('div');
    commentElement.setAttribute('id', `commentElement${comment.post.id + comment.id}`);
    commentElement.classList.add('d-flex', 'justify-content-center', 'mt-3', 'mb-3', 'd-none');
    commentElement.innerHTML = `
        <div class="card border-primary-subtle" style="width: 50rem;">
            <div class="card-body">
                <div class="d-flex">
                    <span class="text-primary fs-5">${comment.user.username}</span>
                    <span class="ms-auto text-secondary align-self-center border-start border-primary-subtle ps-2">${comment.date}, ${comment.time}</span>
                </div>
                <hr class="text-primary">
                <p class="fs-5">${comment.text}</p>
            </div>
        </div>
    `;
    commentSection.append(commentElement);
}

// function createPost(post) {
//     post.id = posts.length + 1;
//     // ......
//     posts.push(post);
// }

function createPostElement(post) {
    const postElement = document.createElement('div');
    postElement.setAttribute('id', `postElement${post.id}`);
    postSection.append(postElement);
    postElement.classList.add('card', 'border-primary-subtle', 'mb-4', 'mx-auto');
    postElement.setAttribute('style', 'width: 50rem');
    postElement.innerHTML = `
        <div class="img-div" id="img-div${post.id}">
            <img id="postImage${post.id}" src="../static/images/${post.imageName}" class="card-img-top" alt="...">
        </div>
        <div class="card-body border-bottom border-primary-subtle">
            <div class="d-flex">
                <button id="likeButton${post.id}" class="bg-transparent border-0 p-0">
                    <i id="like${post.id}" class="h2 bi bi-heart text-primary"></i>
                </button>
                <button id="commentButton${post.id}" class="ms-4 align-self-center bg-transparent border-0 p-0" style="margin-top: -5px;">
                    <i class="h2 bi bi-chat text-primary"></i>
                </button>
                <button id="bookmarkButton${post.id}" class="ms-auto bg-transparent border-0 p-0">
                    <i class="h2 bi bi-bookmark text-primary" id="bookmark${post.id}"></i>
                </button>
            </div>
        </div>
        <div class="card-body" id="post${post.id}Desc">
            <p class="card-text fs-5">${post.description}</p>
        </div>
        <div class="card-footer border-primary-subtle rounded-bottom-2 bg-white">
            <div class="d-flex mb-1">
                <span>Posted by: <span class="text-primary fs-6">${post.user.username}</span></span>
                <span class="ms-auto align-self-center text-secondary border-start border-primary-subtle ps-2">${post.date}, ${post.time}</span>
            </div>
        </div>
    `;
    let commentSection = document.createElement('div');
    let form = document.createElement('form');
    commentSection.prepend(form);
    commentSection.setAttribute('id', `commentSection${post.id}`);
    form.classList.add('card-body', 'd-none', 'border-bottom', 'border-primary-subtle');
    form.innerHTML = `<textarea id=post${post.id}Textarea></textarea>` +
        '<button type="submit" class="btn btn-primary ms-auto">Submit</button>';
    let postDesc = document.getElementById(`post${post.id}Desc`);
    postDesc.before(commentSection);
    operatePost(post);
}

function test(post) {
    let commentSection = document.getElementById(`commentSection${post.id}`);
    if (commentSection) {

    }
}

// function toggleCommentSection(post) {
//
// }

function addComment(user, post) {
    let comment = document.createElement('div');
    comment.innerHTML = `<p></p>`;
    let comSec = document.getElementById(`commentSection${post.id}`);
    comSec.after(comment);
}

function toggleLike(post, like) {
    const likeIcon = document.getElementById(`like${post.id}`);
    if (likeIcon.classList.contains('bi-heart')) {
        like = true;
        likeIcon.classList.replace('bi-heart', 'bi-heart-fill');
    } else if (likeIcon.classList.contains('bi-heart-fill')) {
        like = false;
        likeIcon.classList.replace('bi-heart-fill', 'bi-heart');
    }
    return like;
}

function toggleBookmark(post) {
    const bookmark = document.getElementById(`bookmark${post.id}`);
    if (bookmark.classList.contains('bi-bookmark')) {
        bookmark.classList.replace('bi-bookmark', 'bi-bookmark-fill');
    } else if (bookmark.classList.contains('bi-bookmark-fill')) {
        bookmark.classList.replace('bi-bookmark-fill', 'bi-bookmark');
    }
}

function toggleCommentSection(post) {
    const commentSection = document.getElementById(`commentSection${post.id}`);
    const form = commentSection.querySelector('form');
    if (form.classList.contains('d-flex')) {
        form.classList.replace('d-flex', 'd-none');
    } else if (form.classList.contains('d-none')) {
        form.classList.replace('d-none', 'd-flex');
    }
}

function operatePost(post) {
    const pressLike = document.getElementById(`likeButton${post.id}`);
    pressLike.addEventListener('click', () => {
        post.isLiked = toggleLike(post, post.isLiked);
    });
    const pressComment = document.getElementById(`commentButton${post.id}`);
    pressComment.addEventListener('click', () => {
        toggleCommentSection(post);
    });
    const pressBookmark = document.getElementById(`bookmarkButton${post.id}`);
    pressBookmark.addEventListener('click', () => {
        toggleBookmark(post);
    });
    const pressLikeOnImage = document.getElementById(`postImage${post.id}`);
    pressLikeOnImage.addEventListener('dblclick', () => {
        let postIsLiked = toggleLike(post, post.isLiked);
        post.isLiked = postIsLiked;
        if (postIsLiked) {
            const image = document.getElementById(`img-div${post.id}`);
            const likeOnImageOutline = document.createElement('i');
            likeOnImageOutline.classList.add('bi', 'bi-heart-fill', 'text-white', 'img-heart-icon-outline');
            const likeOnImage = document.createElement('i');
            likeOnImage.classList.add('bi', 'bi-heart-fill', 'text-primary', 'img-heart-icon');
            image.append(likeOnImageOutline);
            image.append(likeOnImage);
            setTimeout(() => {
                likeOnImageOutline.remove();
                likeOnImage.remove();
            }, 1500);
        }
    });
}

function addPost(postElement) {
    createPostElement(postElement);
    posts.push(postElement);
}

document.getElementById('uploadForm').addEventListener('submit', function (event) {
    event.preventDefault();
    executeAddingPost();
});

function executeAddingPost() {
    let description = document.getElementById('descText').value;
    let image = document.getElementById('imageFile').files[0];
    let userId = document.getElementById('userId').value;
    if (image) {
        let formData = new FormData();
        formData.append('imageFile', image);
        formData.append('desc', description);
        formData.append('id', userId);

        // axios.post(BASE_URL + POSTS_URL + MAKE_POST, formData, {
        //     headers: {
        //         'Content-Type': 'multipart/form-data'
        //     }
        // })
        // .then(function() {
        //     console.log("Post was created successfully");
        //     // form.classList.add('d-none');
        //     addPost(post);
        //     console.log(post);
        // })
        // .catch(function (error) {
        //     console.log(error.message);
        // });
    }

}