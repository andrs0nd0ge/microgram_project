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
    id: 0,
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
    id: 0,
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
    id: 0,
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

const posts = [firstPost, secondPost, thirdPost];

function setIdsForPosts() {
    for (let i = 0; i < posts.length; i++) {
        posts[i].id = i + 1;
    }
}

setIdsForPosts();

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

addPost(posts, post);

function addPost(posts, post) {
    posts.push(post);
}

console.log(posts);

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

const leaveLike = likePost(posts, 4);

console.log(leaveLike);

function hideSplashScreen() {
    const splashScreen = document.getElementById('splash');
    splashScreen.classList.add('d-none');
    const showSplash = document.getElementById('showSplash');
    showSplash.classList.remove('d-none');
    showOrHidePostSection();
    showOrHideCommentSection();
}

function showSplashScreen() {
    const splashScreen = document.getElementById('splash');
    if (splashScreen.classList.contains('d-none')) {
        splashScreen.classList.remove('d-none');
    }
    showOrHidePostSection();
    showOrHideCommentSection();
}

function showOrHidePostSection() {
    const post = document.getElementById('postSection');
    post.classList.toggle('d-none');
}

function showOrHideCommentSection() {
    const comment = document.getElementById('commentSection');
    if (!comment.classList.contains('d-none')) {
        comment.classList.add('d-none');
    }
}

function createCommentElement(comment) {
    const commentSection = document.createElement('div');
    commentSection.setAttribute('id', 'commentSection');
    document.body.append(commentSection);
    commentSection.classList.add('d-flex', 'justify-content-center', 'mt-3', 'mb-3', 'd-none');
    commentSection.innerHTML = `
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
}

function createPostElement(post) {
    const postSection = document.createElement('div');
    postSection.setAttribute('id', 'postSection');
    document.body.append(postSection);
    postSection.classList.add('card', 'border-primary-subtle', 'mb-4', 'mx-auto', 'd-none');
    postSection.setAttribute('style', 'width: 50rem');
    postSection.innerHTML = `
        <div class="img-div" id="img-div">
            <img id="postImage" src="../static/images/${post.imageName}" class="card-img-top" alt="...">
        </div>
        <div class="card-body border-bottom border-primary-subtle">
            <div class="d-flex">
                <button id="likeButton" class="bg-transparent border-0 p-0"><i id="like" class="h2 bi bi-heart text-primary"></i></button>
                <button class="ms-4 align-self-center bg-transparent border-0 p-0" style="margin-top: -5px;" onclick="toggleCommentSection()"><i class="h2 bi bi-chat text-primary"></i></button>
                <button id="bookmarkButton" class="ms-auto bg-transparent border-0 p-0"><i class="h2 bi bi-bookmark text-primary" id="bookmark"></i></button>
            </div>
        </div>
        <div class="card-body">
            <p class="card-text fs-5">${post.description}</p>
        </div>
        <div class="card-footer border-primary-subtle bg-white">
            <div class="d-flex mb-1">
                <span>Posted by: <span class="text-primary fs-6">${post.user.username}</span></span>
                <span class="ms-auto align-self-center text-secondary border-start border-primary-subtle ps-2">${post.date}, ${post.time}</span>
            </div>
        </div>
    `;
    const pressLike = document.getElementById('likeButton');
    pressLike.addEventListener('click', () => {
        postIsLiked = toggleLike(post.isLiked);
        post.isLiked = postIsLiked;
    })
    const pressBookmark = document.getElementById('bookmarkButton');
    pressBookmark.addEventListener('click', () => {
        toggleBookmark();
    })
    const pressLikeOnImage = document.getElementById('postImage');  
    pressLikeOnImage.addEventListener('dblclick', () => {
        postIsLiked = toggleLike(post.isLiked);
        post.isLiked = postIsLiked;
        if (postIsLiked) {
            const image = document.getElementById('img-div');
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

function toggleLike(like) {
    const likeIcon = document.getElementById('like');
    if (likeIcon.classList.contains('bi-heart')) {
        like = true;
        likeIcon.classList.remove('bi-heart');
        likeIcon.classList.add('bi-heart-fill');
    } else if (likeIcon.classList.contains('bi-heart-fill')) {
        like = false;
        likeIcon.classList.remove('bi-heart-fill');
        likeIcon.classList.add('bi-heart');
    }
    return like;
}

function toggleBookmark() {
    const bookmark = document.getElementById('bookmark');
    if (bookmark.classList.contains('bi-bookmark')) {
        bookmark.classList.remove('bi-bookmark');
        bookmark.classList.add('bi-bookmark-fill');
    } else if (bookmark.classList.contains('bi-bookmark-fill')) {
        bookmark.classList.remove('bi-bookmark-fill');
        bookmark.classList.add('bi-bookmark');
    }
}

function toggleCommentSection() {
    const commentSection = document.getElementById('commentSection');
    commentSection.classList.toggle('d-none');
}

function addPublication(postElement, comment) {
    createPostElement(postElement);
    createCommentElement(comment);
}

addPublication(firstPost, comment);