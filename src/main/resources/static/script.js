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
    imagePath: 'somepic.jpg',
    description: 'some desc',
    date: new Date().toDateString(),
    user: user,
    isLiked: false
};

const secondPost = {
    id: 0,
    imagePath: 'somepic.jpg',
    description: 'some desc',
    date: new Date().toDateString(),
    user: user,
    isLiked: false
};

const thirdPost = {
    id: 0,
    imagePath: 'somepic.jpg',
    description: 'some desc',
    date: new Date().toDateString(),
    user: user,
    isLiked: false
};

console.log(firstPost);

const comment = {
    id: 0,
    text: 'some text',
    date: new Date().toDateString(),
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
    date: new Date().toDateString(),
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

const leaveLike = likePost(posts,4);

console.log(leaveLike);