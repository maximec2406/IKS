/**
 * srp-6a protocol authentification client side.
 * This is analog of SRPClientContext java class
 */
var pN;
var pNBitsLen;
var pg = str2bigInt('02', 16, 0);
var pk;
var pabBitsLen = 256;

var pN256 = str2bigInt('115b8b692e0e045692cf280b436735c77a5a9e8a9e7ed56c965f87db5b2a2ece3', 16, 0); // 256 bit
var pk256 = str2bigInt('dbe5dfe0704fee4c85ff106ecd38117d33bcfe50', 16, 0); // for 256 bit N
var pN2048 = str2bigInt('c0e67812bb81d36630372af77b286d35697fc7e45835ec1595ba407b429c15ea61a6c2137d31c8eeadac9e6f27933092ecca8b73a63ea77d65f3cf0bde06cf2d0ed8a0257d2f16f764b4bff73833dcd4be2a26853f554b47a8150f9a376f4243690654feb8e65b0c164620363adb5cdfb14e24bd7c6c9ba889b6ba967e9a369c2baf5d3500d0dbfaa608a03c3b19c83434203f5f793e4f996b61bd1fc7cbe982fef65ef20633402fa3b3880fcccc9fadaacce241c320c21a5917ec63da57fdb9d8a7b66dfa2fc5c1d4a416de06000e02823e9082e75c26cd0ac71dc6b8ee8aff1028f185e73fdb9e9aa9cf2923f70b4d7bbef172a343a7bba48781e54961aa7b', 16, 0); // 2048 bit
var pk2048 = str2bigInt('1b4a84af4ee4b654bda282e742190299c7e5fdac', 16, 0); // for 2048 bit N

function SRPClientContext(salt, vBbytes, ext) {
    var extended = (ext === true);
    pN = extended ? pN2048 : pN256;
    pNBitsLen = extended ? 2048 : 264;
    pk = extended ? pk2048 : pk256;

    this.vS = salt;
    this.vBbs = vBbytes;
    this.va = randBigInt(pabBitsLen, 1);
    // A = g^a % N
    this.vA = powMod(pg, this.va, pN);
}

SRPClientContext.prototype.makeAuthorizationData = function (login, password) {
    var vUserPasswordHash = calcSHA1(login + ":" + (password == null ? "" : password), false);
    var vB = str2bigInt(this.vBbs, 16, 1);

    // calculate x = SHA(s | SHA(U | ":" | p))
    var vx = this.xCalculate(vUserPasswordHash, this.vS);
    // calculate u = SHA(A || B)
    var vu = srp_compute_u(this.getAbytes(), this.vBbs);

    // Check correct server data
    // The user will abort if he receives B == 0 (mod N) or u == 0.
    if (isZero(mod(vB, pN)) || isZero(vu)) {
        throw "Bad SRP server data";
    }

    // calculate S = (B - kg^x) ^ (a + ux) % N
    var kgN = powMod(pg, vx, pN);
    kgN = mult(kgN, pk);

    var base = add(vB, mult(pN, pk));
    base = sub(base, kgN);
    base = mod(base, pN);

    var exp = add(this.va, mult(vx, vu)); // a + ux
    var vS = powMod(base, exp, pN);

    return calcSHA1Hex(bigInt2str(vS, 16));
};

SRPClientContext.prototype.getAbytes = function () {
    return bigInt2str(this.vA, 16);
};

SRPClientContext.prototype.xCalculate = function (vUserPasswordHash, vs) {
    var h = calcSHA1Hex(vs + vUserPasswordHash);
    var x = str2bigInt(h, 16);
    if (greater(x, pN)) {
        x = mod(x, pN);
    }
    return x;
};

/*
 * SRP-3: u = first 32 bits (MSB) of SHA-1(B)
 * SRP-6(a): u = SHA(A || B)
 */
function srp_compute_u(vAbytes, vBbytes) {
    var hashin = "";

    /* 6a requires left-padding */
    var nlen = 2 * (pNBitsLen >> 3);
    hashin += srp_nzero(nlen - vAbytes.length) + vAbytes;

    /* 6a requires left-padding; nlen already set above */
    hashin += srp_nzero(nlen - vBbytes.length) + vBbytes;

    var h = calcSHA1Hex(hashin);
    var u = str2bigInt(h, 16, 0);

    if (greater(u, pN)) {
        u = mod(u, pN);
    }
    return u;
}

/* Returns a string with n zeroes in it */
function srp_nzero(n) {
    if (n < 1) {
        return "";
    }
    var t = srp_nzero(n >> 1);
    if ((n & 1) == 0) {
        return t + t;
    } else {
        return t + t + "0";
    }
}
