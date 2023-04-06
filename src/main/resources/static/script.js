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

const thirdPost = {
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
    post: firstPost
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
    time: new Date().toLocaleTimeString(),
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
    const commentSection = document.getElementById('commentSection');
    commentSection.classList.add('d-flex', 'justify-content-center', 'mt-3', 'mb-3', 'd-none');
    commentSection.innerHTML = `
        <div class="card border-primary-subtle" style="width: 50rem;">
            <div class="card-body">
                <div class="d-flex">
                    <span class="text-primary fs-5">${comment.post.user.username}</span>
                    <span class="ms-auto text-secondary align-self-center border-start border-primary-subtle ps-2">${comment.date}, ${comment.time}</span>
                </div>
                <hr class="text-primary">
                <p class="fs-5">${comment.text}</p>
            </div>
        </div>
    `;
}

function createPostElement(post) {
    const postSection = document.getElementById('postSection');
    postSection.classList.add('card', 'border-primary-subtle', 'mb-4', 'mx-auto', 'd-none');
    postSection.setAttribute('style', 'width: 50rem');
    postSection.innerHTML = `
        <img src="../static/images/${post.imageName}" class="card-img-top" alt="...">
        <div class="card-body">
            <p class="card-text">${post.description}</p>
        </div>
        <div class="card-footer border-primary-subtle bg-white">
            <div class="d-flex mb-3">
                <span>Posted by: <span class="text-primary fs-5">${post.user.username}</span></span>
                <span class="ms-auto align-self-center text-secondary border-start border-primary-subtle ps-2">${post.date}, ${post.time}</span>
            </div>
            <div class="text-center flex-wrap">
                <button class="btn btn-outline-primary mb-2" onclick="toggleCommentSection()">Hide/Show Comment Section</button>
            </div>
        </div>
    `;
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