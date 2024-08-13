const BASE_URL = 'http://localhost:8080';

const COMMENTS_URL = '/comments';

const POSTS_URL = '/posts';

const MAKE_POST = '/make-post';

const COMMENT = '/comment';

const USERS_URL = '/users';

const REGISTER_URL = '/register';

const postSection = document.createElement('div');

function createPostSection() {
    postSection.setAttribute('id', `postSection`);
    postSection.setAttribute('style', 'z-index: 0');
    document.body.append(postSection);
    return postSection;
}

createPostSection();

let postIdCounter = 0;

const user = {
    id: 1,
    name: 'First',
    username: 'first',
    email: 'onetest@test',
    password: '123',
    isAuthorised: false
};

console.log(user);

const firstPost = {
    id: 1,
    image_name: '1666156904606.jpg',
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
    image_name: 'somepic.jpg',
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
    image_name: 'somepic.jpg',
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

let posts;

function fetchPosts() {
    fetch(BASE_URL + POSTS_URL + '/main')
        .then(response => response.json())
        .then(data => {
            posts = data
        })
        .then(() => {
            for (let i = 0; i < posts.length; i++) {
                addPost(posts[i]);
            }
        })
        .catch(error => console.log(error));
}

fetchPosts();

function authorizeUser(user) {
    user.isAuthorised = true;
}

authorizeUser(user);

console.log(user);

// function likePost(posts, postId) {
//     if (postId >= posts[0].id && postId <= posts.length) {
//         let currentPost = posts[postId - 1];
//         currentPost.isLiked = !currentPost.isLiked;
//         return currentPost;
//     }
// }

// likePost(posts, 4);

function toggleSplashScreen() {
    const splashScreen = document.getElementById('bg');

    if (splashScreen.classList.contains('d-flex')) {
        splashScreen.classList.replace('d-flex', 'd-none');
    } else if (splashScreen.classList.contains('d-none')) {
        splashScreen.classList.replace('d-none', 'd-flex');
    }
}

function createPostElement(post) {
    const postElement = document.createElement('div');
    postElement.setAttribute('id', `postElement${post.id}`);
    postSection.append(postElement);
    postElement.classList.add('card', 'border-primary-subtle', 'mb-4', 'mx-auto');
    postElement.setAttribute('style', 'width: 50rem');
    postElement.innerHTML = `
        <div class="img-div" id="img-div${post.id}">
            <img id="postImage${post.id}" src="../static/images/${post.image_name}" class="card-img-top" alt="...">
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

    createCommentSectionFor(post);

    operatePost(post);
}

function fetchCommentsFor(post) {
    let comments;

    fetch(BASE_URL + COMMENTS_URL + `?post_id=${post.id}`)
        .then(response => response.json())
        .then(data => {
            comments = data
        })
        .then(() => {
            for (let i = 0; i < comments.length; i++) {
                addComment(post, comments[i]);
            }
        })
        .catch(error => console.log(error));
}

function addComment(post, comment) {
    createCommentElement(post, comment);
    // comments.push(comment);
}

function createCommentElement(post, comment) {
    const commentSection = document.getElementById(`commentSection${post.id}`);

    const commentDiv = document.createElement('div');
    commentDiv.classList.add('card-body', 'border-top', 'border-primary-subtle');
    const commentContent = document.createElement('p');
    const commentAuthor = document.createElement('span');
    const commentTime = document.createElement('span');
    commentContent.classList.add('border-top', 'mt-2');
    commentAuthor.classList.add('text-primary', 'pb-2');
    commentTime.classList.add('text-secondary', 'border-start', 'ps-2', 'ms-2');

    commentContent.innerText = comment.text;
    commentAuthor.innerText = comment.user.username;
    commentTime.innerText = new Date(comment.date).toLocaleDateString('ru-RU', {
        hour: '2-digit',
        minute: '2-digit'
    });

    commentDiv.append(commentContent);
    commentContent.before(commentAuthor);
    commentAuthor.after(commentTime);

    commentSection.append(commentDiv);
}

function createCommentSectionFor(post) {
    const commentSection = document.createElement('div');
    const form = document.createElement('form');
    commentSection.prepend(form);
    commentSection.setAttribute('id', `commentSection${post.id}`);
    commentSection.classList.add('d-none');
    form.classList.add('card-body', 'd-none', 'border-top', 'border-primary-subtle');

    form.innerHTML = `<textarea class="border-secondary rounded" id="post${post.id}Textarea" cols="70" name="comment"></textarea>` +
        `<input type="hidden" id="commentUserIdPostId${post.id}" name="user_id" value="${user.id}">` +
        `<input type="hidden" id="commentPostId${post.id}" name="post_id" value="${post.id}">` +
        `<button id="commentSubmitPost${post.id}" type="submit" class="btn btn-primary ms-auto">Submit</button>`;
    const postDesc = document.getElementById(`post${post.id}Desc`);
    postDesc.after(commentSection);

    form.addEventListener('submit', (e) => {
        e.preventDefault();

        const textarea = document.getElementById(`post${post.id}Textarea`);

        const comment = document.createElement('div');
        comment.classList.add('card-body', 'border-top', 'border-primary-subtle');
        const commentContent = document.createElement('p');
        const commentAuthor = document.createElement('span');
        const commentTime = document.createElement('span');
        commentContent.classList.add('border-top', 'mt-2');
        commentAuthor.classList.add('text-primary', 'pb-2');
        commentTime.classList.add('text-secondary', 'border-start', 'ps-2', 'ms-2');

        const commentValue = textarea.value;

        commentContent.innerText = commentValue;
        commentAuthor.innerText = user.username;
        commentTime.innerText = new Date().toLocaleDateString('ru-RU', {
            hour: '2-digit',
            minute: '2-digit'
        });

        comment.append(commentContent);
        commentContent.before(commentAuthor);
        commentAuthor.after(commentTime);

        commentSection.append(comment);

        const userId = document.getElementById(`commentUserIdPostId${post.id}`).value;
        const postId = document.getElementById(`commentPostId${post.id}`).value;

        if (commentValue) {
            fetch(BASE_URL + POSTS_URL + COMMENT, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    user_id: userId,
                    post_id: postId,
                    comment: commentValue
                })
            })
                .then(data => {
                    console.log(data);
                })
                .catch(error => {
                    console.log(error);
                });
        }

        textarea.value = '';
    });
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

    if (commentSection.classList.contains('d-block') && form.classList.contains('d-flex')) {
        commentSection.classList.replace('d-block', 'd-none');
        form.classList.replace('d-flex', 'd-none');

        commentSection.replaceChildren(commentSection.firstElementChild);
    } else if (commentSection.classList.contains('d-none') && form.classList.contains('d-none')) {
        commentSection.classList.replace('d-none', 'd-block');
        form.classList.replace('d-none', 'd-flex');

        fetchCommentsFor(post);
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
        const postIsLiked = toggleLike(post, post.isLiked);
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

function createPostUploadForm() {
    const form = document.createElement('form');
    form.classList.add('d-flex', 'mt-5', 'justify-content-center');
    form.setAttribute('encrypt', 'multipart/form-data');
    form.setAttribute('id', 'postUploadForm');

    const image = document.createElement('input');
    image.setAttribute('id', 'imageFile');
    image.setAttribute('type', 'file');
    image.setAttribute('name', 'file');
    form.append(image);

    const description = document.createElement('input');
    description.setAttribute('id', 'descText');
    description.setAttribute('type', 'text');
    description.setAttribute('name', 'desc');
    description.setAttribute('placeholder', 'Enter description...');
    form.append(description);

    const userId = document.createElement('input');
    userId.setAttribute('id', 'userId');
    userId.setAttribute('type', 'hidden');
    userId.setAttribute('name', 'userId');
    userId.setAttribute('value', `${user.id}`);
    form.append(userId);

    const submitBtn = document.createElement('button');
    submitBtn.setAttribute('type', 'submit');
    submitBtn.classList.add('btn', 'border-primary', 'rounded-start-0');
    submitBtn.innerText = 'Make Post';
    form.append(submitBtn);

    const mainNavbar = document.getElementById('mainNavbar');
    mainNavbar.after(form);
}

createPostUploadForm();

document.getElementById('postUploadForm').addEventListener('submit', function (event) {
    event.preventDefault();
    executeAddingPost();
});

function addPost(postElement) {
    createPostElement(postElement);
    // posts.push(postElement);
}

function executeAddingPost() {
    const description = document.getElementById('descText').value;
    const image = document.getElementById('imageFile').files[0];
    const userId = document.getElementById('userId').value;
    if (image && description) {
        const formData = new FormData();
        formData.append('imageFile', image);
        formData.append('desc', description);
        formData.append('id', userId);

        fetch(BASE_URL + POSTS_URL + MAKE_POST, {
            method: 'POST',
            body: formData
        })
            .then(() => {
                console.log("Post was created successfully");

                postIdCounter++;

                const post = {
                    id: postIdCounter,
                    image_name: image.name,
                    'description': description,
                    date: new Date().toLocaleDateString('ru-RU', {
                        day: '2-digit',
                        month: '2-digit',
                        year: 'numeric'
                    }),
                    time: new Date().toLocaleTimeString('ru-RU', {
                        hour: '2-digit',
                        minute: '2-digit'
                    }),
                    user: {
                        id: userId,
                        name: 'First',
                        username: 'first',
                        email: 'onetest@test',
                        password: '123',
                        isAuthorised: false
                    },
                    isLiked: false
                };

                addPost(post);

                document.getElementById('postUploadForm').reset();

                console.log(post);
            })
            .catch(error => {
                console.log(error);
            });
    }
}

document.getElementById('registration-form').addEventListener('submit', registerUser);

function registerUser(event) {
    event.preventDefault();

    const registrationForm = document.getElementById('registration-form');

    const name = document.getElementById('nameInput').value;
    const username = document.getElementById('usernameInput').value;
    const email = document.getElementById('emailInput').value;
    const password = document.getElementById('passwordInput').value;

    fetch(BASE_URL + USERS_URL + REGISTER_URL, {
        method: 'POST',
        mode: 'cors',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            username: username,
            email: email,
            password: password
        })
    })
        .then(() => {
            console.log('User was registered successfully');

            registrationForm.reset();
        })
        .catch(error => {
            console.log(error);
        });
}