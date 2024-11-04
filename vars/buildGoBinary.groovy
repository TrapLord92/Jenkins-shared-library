#!/usr/bin/env groovy

def call() {
    dir('greenlight'){
    echo "Building Go binary"
    // Compile Go binary
     sh 'go build -o app ./cmd/api'
    echo "Build complete"
}}