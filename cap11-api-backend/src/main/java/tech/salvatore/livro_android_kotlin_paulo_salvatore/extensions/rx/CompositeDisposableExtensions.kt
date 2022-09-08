package tech.salvatore.livro_android_kotlin_paulo_salvatore.extensions.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

object CompositeDisposableExtensions {
    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        this.add(disposable)
    }
}
