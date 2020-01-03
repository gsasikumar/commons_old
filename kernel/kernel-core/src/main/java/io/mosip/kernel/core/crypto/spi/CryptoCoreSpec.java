package io.mosip.kernel.core.crypto.spi;

/**
 * This interface is specification for <b> Core Cryptographic Operations</b>.
 * 
 * The user of this interface will have all cryptographic basic operations like
 * {@link #asymmetricEncrypt(Object, Object)} ,
 * {@link #asymmetricDecrypt(Object, Object)} ,
 * {@link #symmetricEncrypt(Object, Object)} ,
 * {@link #symmetricEncrypt(Object, Object)} , {@link #hash(Object, Object)} ,
 * {@link #sign(Object, Object)} ,
 * {@link #verifySignature(Object, Object, Object)}, {@link #random()}.
 * 
 * @author Urvil Joshi
 * 
 * @since 1.0.0
 *
 * @param <R> the return type of data
 * @param <D> the type of input data
 * @param <S> the type of symmetric key
 * @param <P> the type of public key
 * @param <K> the type of private key
 * @param <T> the type of signature
 */
public interface CryptoCoreSpec<R, D, S, P, K, T> {

	/**
	 * This method is used for core <b> Symmetric Encryption </b>.
	 * 
	 * Symmetric encryption is a type of encryption where only one key (a secret
	 * key) is used to both encrypt and decrypt electronic information. The entities
	 * communicating via symmetric encryption must exchange the key so that it can
	 * be used in the decryption process. This encryption method differs from
	 * asymmetric encryption where a pair of keys, one public and one private, is
	 * used to encrypt and decrypt messages.
	 * 
	 * By using symmetric encryption algorithms, data is converted to a form that
	 * cannot be understood by anyone who does not possess the secret key to decrypt
	 * it. Once the intended recipient who possesses the key has the message, the
	 * algorithm reverses its action so that the message is returned to its original
	 * and understandable form. The secret key used by the sender and recipient
	 * could be a specific password/code or it can be random string of letters or
	 * numbers that have been generated by a secure random number generator (RNG).
	 * 
	 * Symmetric Encryption is described in <a href=
	 * "https://en.wikipedia.org/wiki/Symmetric-key_algorithm">Symmetric-key_algorithm</a>
	 * 
	 * AEAD is Authenticated Encryption with Associated Data which provides both
	 * confidentiality and data origin authentication. AEAD is described in <a href=
	 * "https://tools.ietf.org/html/rfc5116#section-3.3">rfc5116#section-3.3</a>.
	 * 
	 * AAD(Advance Authentication Data) The aim of AAD is to attach information to
	 * the ciphertext that is not encrypted, but is bound to the ciphertext in the
	 * sense that it cannot be changed or separated. Conceptually, the MAC is
	 * computed over the AAD and the ciphertext together.
	 * 
	 * @param key  Symmetric Key as key
	 * @param data data to encrypt
	 * @param aad  Advance Authentication Data
	 * @return encrypted data
	 */
	R symmetricEncrypt(S key, D data, D aad);

	/**
	 * This method is used for core <b> Symmetric Encryption </b>.
	 * 
	 * Symmetric encryption is a type of encryption where only one key (a secret
	 * key) is used to both encrypt and decrypt electronic information. The entities
	 * communicating via symmetric encryption must exchange the key so that it can
	 * be used in the decryption process. This encryption method differs from
	 * asymmetric encryption where a pair of keys, one public and one private, is
	 * used to encrypt and decrypt messages.
	 * 
	 * By using symmetric encryption algorithms, data is converted to a form that
	 * cannot be understood by anyone who does not possess the secret key to decrypt
	 * it. Once the intended recipient who possesses the key has the message, the
	 * algorithm reverses its action so that the message is returned to its original
	 * and understandable form. The secret key used by the sender and recipient
	 * could be a specific password/code or it can be random string of letters or
	 * numbers that have been generated by a secure random number generator (RNG).
	 * 
	 * Symmetric Encryption is described in <a href=
	 * "https://en.wikipedia.org/wiki/Symmetric-key_algorithm">Symmetric-key_algorithm</a>
	 * 
	 * AEAD is Authenticated Encryption with Associated Data which provides both
	 * confidentiality and data origin authentication. AEAD is described in <a href=
	 * "https://tools.ietf.org/html/rfc5116#section-3.3">rfc5116#section-3.3</a>.
	 * 
	 * AAD(Advance Authentication Data) The aim of AAD is to attach information to
	 * the ciphertext that is not encrypted, but is bound to the ciphertext in the
	 * sense that it cannot be changed or separated.Conceptually, the MAC is
	 * computed over the AAD and the ciphertext together.
	 * 
	 * @param key  Symmetric Key as key
	 * @param data Data to encrypt
	 * @param iv   Initialization vector
	 * @param aad  Advance Authentication Data
	 * @return
	 */
	R symmetricEncrypt(S key, D data, D iv, D aad);

	/**
	 * This method is used for core <b> Symmetric Decryption </b>.
	 * 
	 * Symmetric encryption is a type of encryption where only one key (a secret
	 * key) is used to both encrypt and decrypt electronic information. The entities
	 * communicating via symmetric encryption must exchange the key so that it can
	 * be used in the decryption process. This encryption method differs from
	 * asymmetric encryption where a pair of keys, one public and one private, is
	 * used to encrypt and decrypt messages.
	 * 
	 * By using symmetric encryption algorithms, data is converted to a form that
	 * cannot be understood by anyone who does not possess the secret key to decrypt
	 * it. Once the intended recipient who possesses the key has the message, the
	 * algorithm reverses its action so that the message is returned to its original
	 * and understandable form. The secret key used by the sender and recipient
	 * could be a specific password/code or it can be random string of letters or
	 * numbers that have been generated by a secure random number generator (RNG).
	 * 
	 * Symmetric Encryption is described in <a href=
	 * "https://en.wikipedia.org/wiki/Symmetric-key_algorithm">Symmetric-key_algorithm</a>
	 * 
	 * AEAD is Authenticated Encryption with Associated Data which provides both
	 * confidentiality and data origin authentication. AEAD is described in <a href=
	 * "https://tools.ietf.org/html/rfc5116#section-3.3">rfc5116#section-3.3</a>.
	 * 
	 * AAD(Advance Authentication Data) The aim of AAD is to attach information to
	 * the ciphertext that is not encrypted, but is bound to the ciphertext in the
	 * sense that it cannot be changed or separated. Conceptually, the MAC is
	 * computed over the AAD and the ciphertext together.
	 * 
	 * @param key  Symmetric Key as key
	 * @param data data to decrypt
	 * @param aad  Advance Authentication Data
	 * @return decrypted data
	 */
	D symmetricDecrypt(S key, R data, D aad);

	/**
	 * This method is used for core <b> Symmetric Decryption </b>.
	 * 
	 * Symmetric encryption is a type of encryption where only one key (a secret
	 * key) is used to both encrypt and decrypt electronic information. The entities
	 * communicating via symmetric encryption must exchange the key so that it can
	 * be used in the decryption process. This encryption method differs from
	 * asymmetric encryption where a pair of keys, one public and one private, is
	 * used to encrypt and decrypt messages.
	 * 
	 * By using symmetric encryption algorithms, data is converted to a form that
	 * cannot be understood by anyone who does not possess the secret key to decrypt
	 * it. Once the intended recipient who possesses the key has the message, the
	 * algorithm reverses its action so that the message is returned to its original
	 * and understandable form. The secret key used by the sender and recipient
	 * could be a specific password/code or it can be random string of letters or
	 * numbers that have been generated by a secure random number generator (RNG).
	 * 
	 * Symmetric Encryption is described in <a href=
	 * "https://en.wikipedia.org/wiki/Symmetric-key_algorithm">Symmetric-key_algorithm</a>
	 * 
	 * AEAD is Authenticated Encryption with Associated Data which provides both
	 * confidentiality and data origin authentication.AEAD is described in <a href=
	 * "https://tools.ietf.org/html/rfc5116#section-3.3">rfc5116#section-3.3</a>.
	 * 
	 * AAD(Advance Authentication Data) The aim of AAD is to attach information to
	 * the ciphertext that is not encrypted, but is bound to the ciphertext in the
	 * sense that it cannot be changed or separated.Conceptually, the MAC is
	 * computed over the AAD and the ciphertext together.
	 * 
	 * @param key  Symmetric Key as key
	 * @param data data to decrypt
	 * @param iv   Initialization vector
	 * @param aad  Advance Authentication Data
	 * @return decrypted data
	 */
	D symmetricDecrypt(S key, R data, D iv, D aad);

	/**
	 * This method is used for core <b> Asymmetric Encryption </b>.
	 * 
	 * Asymmetric Encryption is a form of Encryption where keys come in pairs. What
	 * one key encrypts, only the other can decrypt.
	 * 
	 * Frequently (but not necessarily), the keys are interchangeable, in the sense
	 * that if key A encrypts a message, then B can decrypt it, and if key B
	 * encrypts a message, then key A can decrypt it. While common, this property is
	 * not essential to asymmetric encryption.
	 * 
	 * Asymmetric Encryption is also known as Public Key Cryptography, since users
	 * typically create a matching key pair, and make one public while keeping the
	 * other secret.
	 * 
	 * Users can "sign" messages by encrypting them with their private keys. This is
	 * effective since any message recipient can verify that the user's public key
	 * can decrypt the message, and thus prove that the user's secret key was used
	 * to encrypt it. If the user's secret key is, in fact, secret, then it follows
	 * that the user, and not some impostor, really sent the message.
	 * 
	 * Users can send secret messages by encrypting a message with the recipient's
	 * public key. In this case, only the intended recipient can decrypt the
	 * message, since only that user should have access to the required secret key.
	 * 
	 * The key to successful use of Asymmetric Encryption is a Key Management
	 * system, which implements a Public Key Infrastructure. Without this, it is
	 * difficult to establish the reliability of public keys, or even to
	 * conveniently find suitable ones.
	 * 
	 * Asymmetric Encryption is described in <a href=
	 * "https://en.wikipedia.org/wiki/Public-key_cryptography">Public-key_cryptography</a>
	 * 
	 * @param key  Public Key as key
	 * @param data data to encrypt
	 * @return encrypted data
	 */
	R asymmetricEncrypt(P key, D data);

	/**
	 * This method is used for core <b> Asymmetric Decryption </b>.
	 * 
	 * Asymmetric Encryption is a form of Encryption where keys come in pairs. What
	 * one key encrypts, only the other can decrypt.
	 * 
	 * Frequently (but not necessarily), the keys are interchangeable, in the sense
	 * that if key A encrypts a message, then B can decrypt it, and if key B
	 * encrypts a message, then key A can decrypt it. While common, this property is
	 * not essential to asymmetric encryption.
	 * 
	 * Asymmetric Encryption is also known as Public Key Cryptography, since users
	 * typically create a matching key pair, and make one public while keeping the
	 * other secret.
	 * 
	 * Users can "sign" messages by encrypting them with their private keys. This is
	 * effective since any message recipient can verify that the user's public key
	 * can decrypt the message, and thus prove that the user's secret key was used
	 * to encrypt it. If the user's secret key is, in fact, secret, then it follows
	 * that the user, and not some impostor, really sent the message.
	 * 
	 * Users can send secret messages by encrypting a message with the recipient's
	 * public key. In this case, only the intended recipient can decrypt the
	 * message, since only that user should have access to the required secret key.
	 * 
	 * The key to successful use of Asymmetric Encryption is a Key Management
	 * system, which implements a Public Key Infrastructure. Without this, it is
	 * difficult to establish the reliability of public keys, or even to
	 * conveniently find suitable ones.
	 * 
	 * Asymmetric Encryption is described in <a href=
	 * "https://en.wikipedia.org/wiki/Public-key_cryptography">Public-key_cryptography</a>
	 * 
	 * @param key  Private Key as key
	 * @param data data to decrypt
	 * @return decrypted data
	 */
	D asymmetricDecrypt(K key, R data);

	/**
	 * This method is used as core <b> Cryptographic hash </b>.
	 * 
	 * Cryptographic hashing is described in <a href=
	 * "https://en.wikipedia.org/wiki/Cryptographic_hash_function">Cryptographic_hash_function</a>.
	 * 
	 * There are some Standards for password hashing can be found at <a href =
	 * "https://www.owasp.org/index.php/Password_Storage_Cheat_Sheet">OWASP Password
	 * Storage Sheet</a>.
	 * 
	 * Iterations should be included by implementation.
	 * 
	 * The iterations specifies how many times the hash executes its underlying
	 * algorithm. A higher value is safer. You need to experiment on hardware
	 * equivalent to your production systems. As a starting point, find a value that
	 * requires one half second to execute. Scaling to huge number of users is
	 * beyond the scope of this document. Remember to save the value of iterations
	 * with the hashed password.
	 * 
	 * @param data data to hash
	 * @param salt salt argument should be random data and vary for each user. It
	 *             should be at least 32 bytes long.
	 * @return hashed data
	 */
	T hash(D data, D salt);

	/**
	 * This method is responsible for core <b> Digital Signature </b>.
	 * 
	 * This method is for signing data.
	 * 
	 * A digital signature is a mathematical technique used to validate the
	 * authenticity and integrity of a message, software or digital document. As the
	 * digital equivalent of a handwritten signature or stamped seal, a digital
	 * signature offers far more inherent security, and it is intended to solve the
	 * problem of tampering and impersonation in digital communications.
	 * 
	 * Digital signatures can provide the added assurances of evidence of origin,
	 * identity and status of an electronic document, transaction or message and can
	 * acknowledge informed consent by the signer.
	 * 
	 * In many countries, including the United States, digital signatures are
	 * considered legally binding in the same way as traditional document
	 * signatures.
	 * 
	 * Digital Signature is described in <a href=
	 * "https://en.wikipedia.org/wiki/Digital_signature">Digital_signature</a>.
	 * 
	 * @param data       data to sign
	 * @param privateKey privateKey of owner
	 * @return signed data
	 */
	T sign(D data, K privateKey);

	/**
	 * This method is responsible for core <b> Digital Signature </b>.
	 * 
	 * This method verifies signature.
	 * 
	 * A digital signature is a mathematical technique used to validate the
	 * authenticity and integrity of a message, software or digital document. As the
	 * digital equivalent of a handwritten signature or stamped seal, a digital
	 * signature offers far more inherent security, and it is intended to solve the
	 * problem of tampering and impersonation in digital communications.
	 * 
	 * Digital signatures can provide the added assurances of evidence of origin,
	 * identity and status of an electronic document, transaction or message and can
	 * acknowledge informed consent by the signer.
	 * 
	 * In many countries, including the United States, digital signatures are
	 * considered legally binding in the same way as traditional document
	 * signatures.
	 * 
	 * Digital Signature is described in <a href=
	 * "https://en.wikipedia.org/wiki/Digital_signature">Digital_signature</a>
	 * 
	 * @param data      data to sign
	 * @param signature signed data
	 * @param publicKey public key of owner
	 * @return True; if signature is verified;False otherwise
	 */
	boolean verifySignature(D data, T signature, P publicKey);

	/**
	 * This method is responsible for Cryptographically Secure Pseudorandom Number
	 * Generator (RNG).
	 * 
	 * <p>
	 * A cryptographically strong random number minimally complies with the
	 * statistical random number generator tests specified in
	 * <a href="http://csrc.nist.gov/cryptval/140-2.htm"> <i>FIPS 140-2, Security
	 * Requirements for Cryptographic Modules</i></a>, section 4.9.1.
	 * 
	 * RNG is described in <a href =
	 * "https://en.wikipedia.org/wiki/Cryptographically_secure_pseudorandom_number_generator">Cryptographically_secure_pseudorandom_number_generator</a>
	 * 
	 * @param <U> the type of random
	 * 
	 * @return RNG
	 */
	<U> U random();

}
